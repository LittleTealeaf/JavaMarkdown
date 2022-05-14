package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownItem;

import java.io.IOException;
import java.io.Writer;

public abstract class Markup extends InlineItem {

    protected final String syntax;
    protected final Object object;

    public Markup(Object object, String syntax) throws IllegalContentsException {
        this.object = object;
        this.syntax = syntax;
        checkType(object);
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        if(object instanceof MarkdownItem) {
            return ((MarkdownItem) object).toWriter(writer.append(syntax)).append(syntax);
        } else {
            return writer.append(syntax).append(object.toString()).append(syntax);
        }
    }

    @Override
    public String asString() {
        return String.format("%s%s%s", syntax,object.toString(),syntax);
    }
}
