package com.zych.services;

import com.zych.model.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class CsvOutputGeneratorTest {

    private LinkedList<Word[]> input;
    private CsvOutputGenerator outputGenerator;

    @BeforeEach
    public void setup() {
        input = new LinkedList<>();
        input.add(new Word[]{new Word("Ala"), new Word("ma"), new Word("kota")});
        input.add(new Word[]{new Word("Kot"), new Word("nie"), new Word("ma"), new Word("Ali")});
        outputGenerator = new CsvOutputGenerator();
    }

    @Test
    void generateOutput() {
        String s = outputGenerator.generateOutput(input);
        String expected = "Sentence 1, Ala, ma, kota\nSentence 2, Kot, nie, ma, Ali\n";
        Assertions.assertThat(s).isEqualTo(expected);
    }

    @Test
    void generateHeader() {
        outputGenerator.generateOutput(input);
        String s = outputGenerator.generateHeader();
        String expected = ", Word 1, Word 2, Word 3, Word 4";

        Assertions.assertThat(s).isEqualTo(expected);
    }
}