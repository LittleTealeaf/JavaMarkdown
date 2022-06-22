package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Structure;

public class Table extends Structure {

    @Override
    public String asString() {
        return null;
    }

    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }
}
