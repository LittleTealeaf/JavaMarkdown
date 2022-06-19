package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

public class Table extends MarkdownElement  {

    @Override
    public String asString() {
        return null;
    }

    @Override
    public boolean requiresNewlineBefore() {
        return false;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return false;
    }

    @Override
    protected <T> T checkType(T item) throws IllegalContentsException {
        return null;
    }
}
