package com.zych.io;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.*;

@Setter
@Getter
@Log4j2
public class OutputFile {

    private String fileName;
    private BufferedWriter writer = null;

    public OutputFile(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(new File(fileName)));
    }

    public void write(String output) throws IOException {
        writer.append(output);
    }

    public void close() throws IOException {
        writer.close();
    }
}
