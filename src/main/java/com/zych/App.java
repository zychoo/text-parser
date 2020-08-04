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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

@Log4j2
@RequiredArgsConstructor
public class App {

    private final InputReader inputReader;
    private OutputGenerator outputGenerator;

    public void run() throws IOException {

        log.info("Starting APP");

        String input;
        Map<Sentence, Word[]> sentenceMap;

        File csv = new File("output.csv");
        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csv));
        File xml = new File("output.xml");
        BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(xml));

        OutputGenerator csvOutputGenerator = new CsvOutputGenerator();
        OutputGenerator xmlOutputGenerator = new XmlOutputGenerator();

        while ((input = inputReader.read()) != null) {
            sentenceMap = createSentenceToWordsMap(input);
            String csvOutput = csvOutputGenerator.generateOutput(sentenceMap.values());
            csvWriter.write(csvOutput);
        }

        csvWriter.write(csvOutputGenerator.generateHeader());

        csvWriter.close();
        xmlWriter.close();

        log.info("App Done");
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

    private void writeXmlBatch(Map<Sentence, Word[]> sentenceMap) throws IOException {
        OutputFile xmlOutputFile = new OutputFile("output.xml");
        outputGenerator = new XmlOutputGenerator();
        String xmlOutput = outputGenerator.generateOutput(sentenceMap.values());
        xmlOutputFile.write(xmlOutput);
    }

}
