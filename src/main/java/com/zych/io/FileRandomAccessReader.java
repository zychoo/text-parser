package com.zych.io;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static com.zych.Constants.READ_BUFFER_LENGTH;

@Log4j2
public class FileRandomAccessReader implements InputReader {

    private RandomAccessFile raf;
    private boolean endOfFileReached = false;

    public FileRandomAccessReader(String inputFileName) throws IOException {
        File file = new File(inputFileName);
        this.raf = new RandomAccessFile(file, "r");
    }

    @Override
    public String read() {

        if (endOfFileReached) return null;

        try {
            int buffLen = READ_BUFFER_LENGTH;
            byte[] bytes;
            long fileSize = raf.length();

            long left = fileSize - raf.getFilePointer();

            /*
            * Read small file in one piece.
            */
            if (left < buffLen) {
                bytes = new byte[(int)left];
                endOfFileReached = true;
                raf.read(bytes, 0, (int) left);
                return new String(bytes);
            }

            bytes = new byte[buffLen];

            int readLength = buffLen / 2;
            raf.read(bytes, 0, readLength);

            int endOfChunk;
            for (int i = readLength;; i++) {
                int b = raf.read();
                if (b == 13 || b == 10) {
                    endOfChunk = i;
                    break;
                } else if (b == -1) {
                    endOfChunk = i;
                    endOfFileReached = true;
                    break;
                }
                bytes[i] = (byte)b;
            }
            return new String(bytes).substring(0, endOfChunk);
        } catch (IOException e) {
            log.error("Cannot read from input file.", e);
        }
        return "";
    }
}
