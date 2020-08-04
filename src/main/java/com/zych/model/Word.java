package com.zych.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Word implements Comparable<Word>{

    private final String value;

    @Override
    public int compareTo(Word otherWord) {
        return value.compareToIgnoreCase(otherWord.getValue());
    }

    @Override
    public String toString() {
        return value;
    }
}
