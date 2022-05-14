package org.tealeaf.javamarkdown.archive.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownItem;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@Deprecated
public abstract class Markup extends MarkdownItem {

    private static final Set<Class<? extends MarkdownItem>> ILLEGAL_TYPES;

    static {
        ILLEGAL_TYPES = Set.of();
    }

    private final Object object;
    private final String syntax;

    public Markup(Object object, String syntax) throws IllegalContentsException {
        this.object = object;
        this.syntax = syntax;
        testIllegalTypes(object,ILLEGAL_TYPES);
    }

    @Override
    protected void checkType(Object object) throws IllegalContentsException {

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
        return String.format("%s%s%s", syntax,object,syntax);
    }
}
