package com.zych.io;

public class HardcodedInputReader implements InputReader{
    @Override
    public String read() {
        return "Mary had a little lamb. Peter called for the wolf, and Aesop came.\n" +
                "Cinderella likes shoes.";
    }
}
