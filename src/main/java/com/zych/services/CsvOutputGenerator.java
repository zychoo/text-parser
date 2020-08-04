package com.zych.services;

import com.zych.model.Word;

import java.util.Iterator;

public class CsvOutputGenerator implements OutputGenerator {

    @Override
    public String generateOutput(Iterable<Word[]> sentences) {

            StringBuilder sb = new StringBuilder();
            Iterator<Word[]> iterator = sentences.iterator();
            int i = 1;
            int maxWordsCount = 0;
            while (iterator.hasNext()) {
                Word[] words = iterator.next();
                maxWordsCount = Math.max(words.length, maxWordsCount);
                sb.append("Sentence ").append(i);
                generateCsvOutputLine(words, sb);
                sb.append("\n");
                i++;
            }
            String wordsString = sb.toString();
            sb = new StringBuilder();
            for (int j = 1; j <= maxWordsCount; j++) {
                sb.append(", Word ").append(j);
            }
            sb.append("\n");

            return sb.toString() + wordsString;

    }

    private void generateCsvOutputLine(Word[] words, StringBuilder sb) {
        for (Word w : words) {
            sb.append(", ").append(w);
        }
    }
}
