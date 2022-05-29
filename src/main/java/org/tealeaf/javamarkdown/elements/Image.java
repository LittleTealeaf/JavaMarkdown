package org.tealeaf.javamarkdown.elements;

public class Image extends Link {

    public Image(String content, String src) {
        super(content, src);
    }

    @Override
    public String asString() {
        return String.format("!%s", super.asString());

    }
}
