package org.tealeaf.javamarkdown.archive.iterables;

import org.tealeaf.javamarkdown.MarkdownItem;

import java.io.IOException;
import java.io.Writer;

@Deprecated
public abstract class MarkdownList extends MarkdownItem {




    @Override
    public String asString() {
        return null;
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return null;
    }
}
