package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

/**
 *
 * @param <T> The class type to return in append methods
 * @since 0.0.13
 * @author Thomas Kwashnak
 */
public interface MarkdownCompiler <T extends MarkdownCompiler<?>> {
    T appendString(String string);
    T appendMarkdownElement(MarkdownElement element);
    default T appendBold(Object content) {
        return appendMarkdownElement(new Bold(content));
    }
    default T appendCode(Object content) {
        return appendMarkdownElement(new Code(content));
    }
    default T appendStrikethrough(Object content) {
        return appendMarkdownElement(new Strikethrough(content));
    }
    default T appendItalic(Object content) {
        return appendMarkdownElement(new Italic(content));
    }

    default T appendBulletList(Object[] objects) {
        return appendMarkdownElement(new BulletList(objects));
    }

    default T appendBulletList(String name, Object[] objects) {
        return appendMarkdownElement(new BulletList(name,objects));
    }

    default T appendCodeBlock(Object content) {
        return appendMarkdownElement(new CodeBlock(content));
    }

    default T appendCodeBlock(String language, Object content) {
        return appendMarkdownElement(new CodeBlock(language,content));
    }

    default T appendHeader(Object content) {
        return appendMarkdownElement(new Header(content));
    }

    default T appendHeader(int level, Object content) {
        return appendMarkdownElement(new Header(level,content));
    }

    default T appendImage(String src) {
        return appendMarkdownElement(new Image(src));
    }

    default T appendImage(String content, String src) {
        return appendMarkdownElement(new Image(content,src));
    }

    default T appendLink(Object content, String url) {
        return appendMarkdownElement(new Link(content,url));
    }

    default T appendLink(String url) {
        return appendMarkdownElement(new Link(url));
    }

    default T appendNumberedList(Object[] objects) {
        return appendMarkdownElement(new NumberedList(objects));
    }

    default T appendNumberedList(String name, Object[] objects) {
        return appendMarkdownElement(new NumberedList(name,objects));
    }

}
