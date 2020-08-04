package com.zych.io;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Log4j2
public class FileInputReader implements InputReader{

    BufferedReader reader;

    public FileInputReader(String inputFileName) throws IOException {
        File file = new File(inputFileName);
        reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public String read() {
        try {
            String s;
            if ((s = reader.readLine()) != null) {
                return s;
            }
            reader.close();
            return null;
        } catch (IOException e) {
            log.error("Error while reading input file", e);
            return null;
        }
    }
}
