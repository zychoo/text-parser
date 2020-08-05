package com.zych;

import com.zych.io.InputReader;
import com.zych.model.Sentence;
import com.zych.model.Word;
import com.zych.services.CsvOutputGenerator;
import com.zych.services.InputParser;
import com.zych.services.OutputGenerator;
import com.zych.services.XmlOutputGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class App {

    private final InputReader inputReader;

    public void run() throws IOException {

        String input;
        Map<Sentence, Word[]> sentenceMap;

        String filename = FilenameUtils.removeExtension(inputReader.getFile().getName());

        File csvTemp = new File("output-temp.csv");
        BufferedWriter csvTempWriter = new BufferedWriter(new FileWriter(csvTemp));
        File xml = new File(filename + ".xml");
        BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(xml));

        OutputGenerator csvOutputGenerator = new CsvOutputGenerator();
        OutputGenerator xmlOutputGenerator = new XmlOutputGenerator();
        InputParser parser = new InputParser();
        xmlWriter.write(xmlOutputGenerator.generateHeader());
        while ((input = inputReader.read()) != null) {
            sentenceMap = parser.createSentenceToWordsMap(input);
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
        String filename = FilenameUtils.removeExtension(inputReader.getFile().getName());
        File csv = new File(filename + ".csv");
        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csv));
        csvWriter.write(csvOutputGenerator.generateHeader());
        BufferedReader csvReader = new BufferedReader(new FileReader(csvTemp));
        String line;
        csvWriter.write("\n");
        while((line = csvReader.readLine()) != null) {
            csvWriter.write(line);
            csvWriter.write("\n");
        }
        csvWriter.close();
        csvReader.close();
        csvTemp.delete();
    }
}
