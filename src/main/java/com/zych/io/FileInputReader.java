package com.zych.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileInputReader implements InputReader{

    BufferedReader reader;

    public FileInputReader(String inputFileName) throws IOException {
        File file = new File(inputFileName);
        reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public String read() {
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(sb::append);
        return sb.toString();
    }
}
