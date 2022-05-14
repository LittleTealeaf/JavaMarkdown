package org.tealeaf.javamarkdown.archive.items;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownItem;

import java.io.IOException;
import java.io.Writer;

@Deprecated
public class FootnoteLink extends MarkdownItem {

    private final int id;

    public FootnoteLink(int id) {
        this.id = id;
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append("[^").append(Integer.toString(id)).append(']');
    }

    @Override
    public String asString() {
        return String.format("[^%d]", id);
    }

    @Override
    protected void checkType(Object object) throws IllegalContentsException {

    }
}
