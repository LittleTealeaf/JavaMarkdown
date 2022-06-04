package org.tealeaf.javamarkdown;

/**
 *
 * @param <T> The class type to return in append methods
 * @since 0.0.13
 * @author Thomas Kwashnak
 */
public interface MarkdownCompiler <T extends MarkdownCompiler<?>> {
    T appendString(String string);
    T appendBold(Object content);
    T appendItalic(Object content);

}
