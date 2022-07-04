package org.tealeaf.javamarkdown.elements;

public class Image extends Link {

    /**
     * @param src Image Source
     *
     * @since 0.0.12
     */
    public Image(String src) {
        super("", src);
    }

    public Image(String content, String src) {
        super(content, src);
    }

    @Override
    public String asString() {
        return String.format("!%s", super.asString());
    }
}
