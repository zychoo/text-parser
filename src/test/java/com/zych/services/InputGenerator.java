package com.zych.services;

import com.zych.model.Word;

import java.util.LinkedList;

public class InputGenerator {

    public static LinkedList<Word[]> generateInput() {
        LinkedList<Word[]> input = new LinkedList<>();
        input.add(new Word[]{new Word("Ala"), new Word("ma"), new Word("kota")});
        input.add(new Word[]{new Word("Kot"), new Word("nie"), new Word("ma"), new Word("Ali")});
        return input;
    }
}
