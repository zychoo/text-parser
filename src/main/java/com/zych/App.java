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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

@Log4j2
@RequiredArgsConstructor
public class App {

    private final InputReader inputReader;
    private OutputGenerator outputGenerator;

    public void run() {

        log.info("Starting APP");

        String input = inputReader.read();
        Map<Sentence, Word[]> sentenceMap;
        do {

            sentenceMap = createSentenceToWordsMap(input);

            try {

                writeCsvBatch(sentenceMap);
                writeXmlBatch(sentenceMap);

            } catch (IOException e) {
                log.error("Could not write to file", e);
            }
        } while ((input = inputReader.read()) != null);
    }

    private Map<Sentence, Word[]> createSentenceToWordsMap(String input) {
        Map<Sentence, Word[]> sentenceMap = new LinkedHashMap<>();

        StringTokenizer sentenceTokenizer = new StringTokenizer(input, ".\t\n\r\f");
        while (sentenceTokenizer.hasMoreTokens()) {
            Sentence s = new Sentence(sentenceTokenizer.nextToken());
            Word[] words = s.generateWordsFromSentence();
            Arrays.sort(words);
            sentenceMap.put(s, words);
        }
        return sentenceMap;
    }


    private void writeCsvBatch(Map<Sentence, Word[]> sentenceMap) throws IOException {
//        int maxWords = 0;
//        maxWords = Math.max(outputGenerator.countMaxSentenceWords(sentenceMap.values(), maxWords));
        OutputFile csvOutputFile = new OutputFile("output.csv");
        outputGenerator = new CsvOutputGenerator();
        String csvOutput = outputGenerator.generateOutput(sentenceMap.values());
        csvOutputFile.write(csvOutput);
    }

    private void writeXmlBatch(Map<Sentence, Word[]> sentenceMap) throws IOException {
        OutputFile xmlOutputFile = new OutputFile("output.xml");
        outputGenerator = new XmlOutputGenerator();
        String xmlOutput = outputGenerator.generateOutput(sentenceMap.values());
        xmlOutputFile.write(xmlOutput);
    }

}
