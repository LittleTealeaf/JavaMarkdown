package org.tealeaf.javamarkdown;

//Change to @since 0.0.12 if this is released before 0.0.12

import java.util.LinkedList;

/**
 * @author Thomas Kwashnak
 * @since 0.0.13
 */
public class MarkdownBuffer implements MarkdownCompiler<MarkdownBuffer> {
    private final LinkedList<Object> items = new LinkedList<>();

    @Override
    public MarkdownBuffer appendString(String string) {
        return null;
    }

    @Override
    public MarkdownBuffer appendMarkdownElement(MarkdownElement element) {
        return null;
    }
}
