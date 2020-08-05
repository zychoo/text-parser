package com.zych.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;

@Getter
@AllArgsConstructor
public class Sentence {

    private final String sentenceString;
    private final String wordDelimiters = "([\\s,\\[\\]{}()<>/\\-:;])+";

    public Word[] generateWordsFromSentence() {
        String[] strings = sentenceString.split(wordDelimiters);

        LinkedList<Word> words = new LinkedList<>();
        for (String string : strings) {
            if (string.isEmpty()) continue;
            words.add(new Word(string));
        }
        Word[] wordArr = new Word[words.size()];
        return words.toArray(wordArr);
    }
}
