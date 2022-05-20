package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.MarkdownElement;

import java.io.IOException;
import java.io.Writer;

public abstract class Markup extends InlineElement {

    protected final String syntax;
    protected final Object object;

    public Markup(Object object, String syntax) {
        this.object = object;
        this.syntax = syntax;
        checkType(object);
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
    public Writer toWriter(Writer writer) throws IOException {
        if(object instanceof MarkdownElement) {
            return ((MarkdownElement) object).toWriter(writer.append(syntax)).append(syntax);
        } else {
            return writer.append(syntax).append(object.toString()).append(syntax);
        }
    }

    @Override
    public String asString() {
        return String.format("%s%s%s", syntax,object.toString(),syntax);
    }
}
