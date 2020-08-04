package com.zych.services;

import com.zych.model.Word;

import java.util.Iterator;

public class CsvOutputGenerator implements OutputGenerator {

    private int maxWords;
    private int sentenceCount = 1;

    @Override
    public String generateOutput(Iterable<Word[]> sentences) {

            StringBuilder sb = new StringBuilder();
            Iterator<Word[]> iterator = sentences.iterator();
            int i = 1;
            maxWords = 0;
            while (iterator.hasNext()) {
                Word[] words = iterator.next();
                maxWords = Math.max(words.length, maxWords);
                sb.append("Sentence ").append(sentenceCount++);
                generateCsvOutputLine(words, sb);
                sb.append("\n");
                i++;
            }

            return sb.toString();
    }

    @Override
    public String generateHeader() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= maxWords; i++) {
            sb.append(", Word ").append(i);
        }
        return sb.toString();
    }

    @Override
    public String generateFooter() {
        return "";
    }

    private void generateCsvOutputLine(Word[] words, StringBuilder sb) {
        for (Word w : words) {
            sb.append(", ").append(w);
        }
    }
}
