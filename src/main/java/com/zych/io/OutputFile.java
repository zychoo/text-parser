package com.zych.io;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Setter
@Getter
@Log4j2
public class OutputFile {

    private String fileName;
    private FileWriter writer = null;

    public OutputFile(String fileName) throws IOException {
        writer = new FileWriter(new File(fileName));
    }

    public void write(String output) throws IOException {
        writer.write(output);
        writer.close();
    }
}
