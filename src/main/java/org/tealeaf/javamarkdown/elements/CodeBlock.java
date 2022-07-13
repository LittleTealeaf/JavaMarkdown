package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Structure;

/**
 * <p>An element that displays its contents within a code block. It can display with syntax highlighting for a specific coding language as long as the markdown renderer
 * supports it</p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.12
 */
public class CodeBlock extends Structure {

    private final String language;
    private final Object content;

    /**
     * <p>Creates a code block with the provided content</p>
     *
     * @param content Content to put within the code block
     *
     * @since 0.0.12
     */
    public CodeBlock(Object content) {
        this.language = null;
        this.content = checkType(content);
    }

    /**
     * <p>Creates a code block with the provided content and a given language</p>
     *
     * @param language Name of the language to render in
     * @param content  Content to put in the code block
     *
     * @since 0.0.12
     */
    public CodeBlock(String language, Object content) {
        this.language = language;
        this.content = checkType(content);
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.12
     */
    @Override
    public String asString() {
        return String.format("```%s\n%s\n```", language == null ? "" : language, content.toString());
    }
}
