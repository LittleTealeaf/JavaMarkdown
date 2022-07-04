package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.InlineElement;

public class Link extends InlineElement {

    private final Object content;
    private final String url;

    /**
     * @param url Link url
     *
     * @since 0.0.12
     */
    public Link(String url) {
        this(url, url);
    }

    public Link(Object content, String url) {
        checkType(content);
        this.content = content;
        this.url = url;
    }

    @Override
    public String asString() {
        return String.format("[%s](%s)", content, url);
    }

    @Override
    public boolean requiresNewlineBefore() {
        return false;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return false;
    }
}
