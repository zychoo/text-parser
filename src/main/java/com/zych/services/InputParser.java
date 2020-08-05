package com.zych.services;

import com.zych.model.Sentence;
import com.zych.model.Word;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputParser {

    public Map<Sentence, Word[]> createSentenceToWordsMap(String input) {
        Map<Sentence, Word[]> sentenceMap = new LinkedHashMap<>();
        String abbreviations = "(?<!Mr|Mrs|Dr)";
        String[] sentences = input.split("(?i)" + abbreviations + "([\\.\\!\\?])+");
        for (String sentence: sentences) {
            Sentence s = new Sentence(sentence);
            Word[] words = s.generateWordsFromSentence();
            Arrays.sort(words);
            sentenceMap.put(s, words);
        }
        return sentenceMap;
    }

}
