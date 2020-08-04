package com.zych;

import com.zych.io.InputReader;
import com.zych.io.OutputFile;
import com.zych.model.Sentence;
import com.zych.model.Word;
import com.zych.services.CsvOutputGenerator;
import com.zych.services.OutputGenerator;
import com.zych.services.XmlOutputGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
public class App {

    private final InputReader inputReader;
    private OutputGenerator outputGenerator;

    public void run() {

        log.info("Starting APP");

        String input = inputReader.read();

        Map<Sentence, Word[]> sentenceMap = createSentenceToWordsMap(input);

        try {
            log.info("Generating CSV output");
            OutputFile csvOutputFile = new OutputFile("output.csv");
            outputGenerator = new CsvOutputGenerator();
            String csvOutput = outputGenerator.generateOutput(sentenceMap.values());
            csvOutputFile.write(csvOutput);
            log.info("CSV Generated successfully");

            log.info("Generating XML output");
            OutputFile xmlOutputFile = new OutputFile("output.xml");
            outputGenerator = new XmlOutputGenerator();
            String xmlOutput = outputGenerator.generateOutput(sentenceMap.values());
            xmlOutputFile.write(xmlOutput);
            log.info("XML Generated successfully");
        } catch (IOException e) {
            log.error("Could not write to file", e);
        }
    }

    private Map<Sentence, Word[]> createSentenceToWordsMap(String input) {
        Map<Sentence, Word[]> sentenceMap = new LinkedHashMap<>();

        StringTokenizer sentenceTokenizer = new StringTokenizer(input, ".!?\n\r\f");
        while (sentenceTokenizer.hasMoreTokens()) {
            Sentence s = new Sentence(sentenceTokenizer.nextToken());
            Word[] words = s.generateWordsFromSentence();
            Arrays.sort(words);
            sentenceMap.put(s, words);
        }
        return sentenceMap;
    }

}
