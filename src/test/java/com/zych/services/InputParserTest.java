package com.zych.services;

import com.zych.model.Sentence;
import com.zych.model.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class InputParserTest {

    @Test
    void createSentenceToWordsMap() {
        InputParser parser = new InputParser();

        Map<Sentence, Word[]> sentenceToWordsMap = parser.createSentenceToWordsMap("Ala ma, kota. Kot ma \nale. \n " +
                "Ala nie ma kota!??!!.... Czy Kot ma Ale?");
        String[] expectedStrings = new String[]{"Ala ma, kota", " Kot ma \nale", " \n Ala nie ma kota", " Czy Kot ma Ale"};
        String[] actualStrings = new String[expectedStrings.length];
        int i = 0;
        for (Sentence sentence : sentenceToWordsMap.keySet()) {
            actualStrings[i++] = sentence.getSentenceString();
        }

        Assertions.assertThat(sentenceToWordsMap.size()).isEqualTo(4);
        Assertions.assertThat(actualStrings).containsExactly(expectedStrings);
    }
}