package com.zych.services;

import com.zych.model.Word;

import java.util.Iterator;
import java.util.stream.Stream;

public class XmlOutputGenerator implements OutputGenerator {
    @Override
    public String generateOutput(Iterable<Word[]> objects) {
        final StringBuilder sb = new StringBuilder();
        sb.append(generateHeader());

        Iterator<Word[]> iterator = objects.iterator();
        while (iterator.hasNext()) {
            sb.append("\t<sentence>\n");
            Word[] words = iterator.next();
            Stream.of(words).forEach(word -> {
                sb.append("\t\t<word>").append(word).append("</word>\n");
            });
            sb.append("\t</sentence>\n");
        }

        sb.append(generateFooter());
        return sb.toString();
    }

    @Override
    public String generateHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>\n";
    }

    @Override
    public String generateFooter() {
        return "</text>";
    }
}
