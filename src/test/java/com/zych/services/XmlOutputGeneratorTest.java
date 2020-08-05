package com.zych.services;

import com.zych.model.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class XmlOutputGeneratorTest {

    private LinkedList<Word[]> input;
    private OutputGenerator outputGenerator;

    @BeforeEach
    public void setup() {
        input = InputGenerator.generateInput();
        outputGenerator = new XmlOutputGenerator();
    }

    @Test
    void generateOutput() {

        String s = outputGenerator.generateOutput(input);
        String expected = "\t<sentence>\n\t\t<word>Ala</word>\n\t\t<word>ma</word>\n\t\t<word>kota</word>\n\t" +
                "</sentence>\n\t<sentence>\n\t\t<word>Kot</word>\n\t\t<word>nie</word>\n\t\t<word>ma</word>\n\t\t" +
                "<word>Ali</word>\n\t</sentence>\n";
        Assertions.assertThat(s).isEqualTo(expected);
    }

    @Test
    void generateHeader() {
        String s = outputGenerator.generateHeader();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>\n";

        Assertions.assertThat(s).isEqualTo(expected);
    }

    @Test
    void generateFooter() {
        String s = outputGenerator.generateFooter();
        String expected = "</text>";
        Assertions.assertThat(s).isEqualTo(expected);
    }
}