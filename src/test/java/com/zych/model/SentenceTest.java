package com.zych.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SentenceTest {

    @Test
    void generateWordsFromSentenceShouldGenerateProperArray() {
        Sentence s = new Sentence("I was just standing there watching Mr. " +
                "Young marching around - he \nwas    furious   ");
        String[] strings = new String[]{"I", "was", "just", "standing", "there", "watching", "Mr.", "Young", "marching",
                "around", "he", "was", "furious"};
        Word[] expexted = new Word[strings.length];
        for (int i = 0; i < strings.length; i++) {
            expexted[i] = new Word(strings[i]);
        }
        Word[] words = s.generateWordsFromSentence();
        Assertions.assertArrayEquals(expexted, words);
    }

    @Test
    void generateWordsFromSentenceShouldOmitWordDelimiters() {
        Sentence s = new Sentence("Hello \t\n\r[]{}()/:;-<>");

        Word[] words = s.generateWordsFromSentence();

        Assertions.assertArrayEquals(new Word[]{new Word("Hello")}, words);
    }
}