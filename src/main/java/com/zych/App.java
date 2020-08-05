package com.zych;

import com.zych.io.InputReader;
import com.zych.model.Sentence;
import com.zych.model.Word;
import com.zych.services.CsvOutputGenerator;
import com.zych.services.OutputGenerator;
import com.zych.services.XmlOutputGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class App {

    private final InputReader inputReader;

    public void run() throws IOException {

        String input;
        Map<Sentence, Word[]> sentenceMap;

        File csvTemp = new File("output-temp.csv");
        BufferedWriter csvTempWriter = new BufferedWriter(new FileWriter(csvTemp));
        File xml = new File("output.xml");
        BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(xml));

        OutputGenerator csvOutputGenerator = new CsvOutputGenerator();
        OutputGenerator xmlOutputGenerator = new XmlOutputGenerator();

        xmlWriter.write(xmlOutputGenerator.generateHeader());
        while ((input = inputReader.read()) != null) {
            sentenceMap = createSentenceToWordsMap(input);
            String csvOutput = csvOutputGenerator.generateOutput(sentenceMap.values());
            csvTempWriter.write(csvOutput);
            String xmlOutput = xmlOutputGenerator.generateOutput(sentenceMap.values());
            xmlWriter.write(xmlOutput);
        }
        xmlWriter.write(xmlOutputGenerator.generateFooter());

        csvTempWriter.close();
        xmlWriter.close();

        addHeaderToCsv(csvTemp, csvOutputGenerator);
    }

    private void addHeaderToCsv(File csvTemp, OutputGenerator csvOutputGenerator) throws IOException {
        log.info("Rewriting csv to add header");
        File csv = new File("output.csv");
        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csv));
        csvWriter.write(csvOutputGenerator.generateHeader());
        BufferedReader csvReader = new BufferedReader(new FileReader(csvTemp));
        String line;
        while((line = csvReader.readLine()) != null) {
            csvWriter.write("\n");
            csvWriter.write(line);
        }
        csvWriter.close();
        csvReader.close();
        csvTemp.delete();
        log.info("Done rewriting csv");
    }

    private Map<Sentence, Word[]> createSentenceToWordsMap(String input) {
        Map<Sentence, Word[]> sentenceMap = new LinkedHashMap<>();
        String abbreviations = "(?<!Mr|Mrs|Dr)";
        String[] sentences = input.split("(?i)" + abbreviations + "[\\.\\!\\?]");
        for (String sentence: sentences) {
            Sentence s = new Sentence(sentence);
            Word[] words = s.generateWordsFromSentence();
            Arrays.sort(words);
            sentenceMap.put(s, words);
        }
        return sentenceMap;
    }
}
