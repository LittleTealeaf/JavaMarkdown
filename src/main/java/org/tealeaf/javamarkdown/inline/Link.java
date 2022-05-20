package org.tealeaf.javamarkdown.inline;

import org.tealeaf.javamarkdown.types.InlineItem;

import java.io.IOException;
import java.io.Writer;

public class Link extends InlineItem {

    private final Object content;
    private final String url;

    public Link(Object content, String url) {
        checkType(content);
        this.content = content;
        this.url = url;
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(asString());
    }

    @Override
    public String asString() {
        return String.format("[%s](%s)", content,url);
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
