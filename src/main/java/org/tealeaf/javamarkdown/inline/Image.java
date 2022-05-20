package org.tealeaf.javamarkdown.inline;

public class Image extends Link {

    public Image(String src) {
        super("", src);
    }

    @Override
    public String asString() {
        return String.format("!%s", super.asString());
    }
}
