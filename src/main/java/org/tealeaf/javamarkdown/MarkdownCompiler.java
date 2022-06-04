package org.tealeaf.javamarkdown;

/**
 *
 * @param <T> The class type to return in append methods
 * @since 0.0.13
 * @author Thomas Kwashnak
 */
public interface MarkdownCompiler <T extends MarkdownCompiler<?>> {
    T appendString(String string);
    T appendMarkdownElement(MarkdownElement element);
    T appendBold(Object content);
    T appendCode(Object content);
    T appendStrikethrough(Object content);
    T appendItalic(Object content);

    T appendBulletList(Object[] objects);

    T appendBulletList(String name, Object[] objects);

    T appendCodeBlock(Object content);

    T appendCodeBlock(String language, Object content);

    T appendHeader(Object content);

    T appendHeader(int level, Object content);

    T appendImage(String src);

    T appendImage(String content, String src);

    T appendLink(Object content, String url);

    T appendLink(String url);

    T appendNumberedList(Object[] objects);

    T appendNumberedList(String name, Object[] objects);

}
