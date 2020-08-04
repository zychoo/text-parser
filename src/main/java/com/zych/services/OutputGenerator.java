package com.zych.services;

import com.zych.model.Word;

public interface OutputGenerator {

    public String generateOutput(Iterable<Word[]> objects);
}
