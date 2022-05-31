package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Structure;

import java.io.IOException;
import java.io.Writer;

public class CodeBlock extends Structure {

    private final String language;
    private final Object content;

    public CodeBlock(Object content) {
        this.language = null;
        this.content = checkType(content);
    }

    public CodeBlock(String language, Object content) {
        this.language = language;
        this.content = checkType(content);
    }

    @Override
    public String asString() {
        return String.format("```%s\n%s\n```", language == null ? "" : language, content.toString());
    }

    /**
     * {@inheritDoc}
     * @return true
     */
    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @return true
     */
    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }
}
