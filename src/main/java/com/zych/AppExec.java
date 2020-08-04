package com.zych;

import com.zych.configuration.LoggerConfiguration;
import com.zych.io.FileRandomAccessReader;
import com.zych.io.InputReader;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.util.Scanner;

@Log4j2
public class AppExec {

    public static void main(String[] args) {

        LoggerConfiguration.setLoggingLevel(Level.INFO);

        log.info("Starting APP");

        String inputFileName;
        if (args.length > 0 && args[0].equals("-f")) {
            inputFileName = args[1];
        } else {
            log.info("Please provide file name with input data");
            Scanner s = new Scanner(System.in);
            inputFileName = s.next();
        }

        InputReader inputReader;
        try {
            inputReader = new FileRandomAccessReader(inputFileName);
            App app = new App(inputReader);
            app.run();
        } catch (IOException e) {
            log.error("Error while writing to output", e);
        }
        log.info("App Done");
    }
}
