package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownComponent;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Markup extends MarkdownComponent {

    private static final Set<Class<? extends MarkdownComponent>> ILLEGAL_TYPES;

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
    public Writer toWriter(Writer writer) throws IOException {
        if(object instanceof MarkdownComponent) {
            return ((MarkdownComponent) object).toWriter(writer.append(syntax)).append(syntax);
        } else {
            return writer.append(syntax).append(object.toString()).append(syntax);
        }
    }


    @Override
    public String asString() {
        return String.format("%s%s%s", syntax,object,syntax);
    }
}
