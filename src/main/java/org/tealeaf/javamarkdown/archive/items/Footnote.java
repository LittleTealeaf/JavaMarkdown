package org.tealeaf.javamarkdown.archive.items;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownItem;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@Deprecated
public class Footnote extends MarkdownItem {


    public Footnote(int id, Object footnote) {

    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    protected void checkType(Object object) throws IllegalContentsException {

    }
}
