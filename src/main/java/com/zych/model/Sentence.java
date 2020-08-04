package com.zych.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringTokenizer;

@Getter
@AllArgsConstructor
public class Sentence {

    private final String sentenceString;
    private final String wordDelimiters = ", -(){}[]<>;:\"\t";

    public Word[] generateWordsFromSentence() {
        StringTokenizer wordTokenizer = new StringTokenizer(sentenceString, wordDelimiters);
        Word[] words = new Word[wordTokenizer.countTokens()];
        int i = 0;
        words[i++] = new Word(wordTokenizer.nextToken());
        while (wordTokenizer.hasMoreTokens()) {
            words[i++] = new Word(wordTokenizer.nextToken());
        }
        return words;
    }
}
