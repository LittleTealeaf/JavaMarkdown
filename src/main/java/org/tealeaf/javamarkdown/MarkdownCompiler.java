package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;

/**
 * @param <T> The class type to return in append methods
 *
 * @author Thomas Kwashnak
 * @since 0.0.14
 */
public interface MarkdownCompiler<T extends MarkdownCompiler<?>> {

    /**
     * <p>Appends a string to the end of the compiler. Forces the basic use, and does not redirect any inputs to {@link #appendMarkdownElement(MarkdownElement)}.</p>
     *
     * @param string Text to append to the end of the compiler
     *
     * @return A reference to the compiler
     *
     * @throws IOException If an I/O Exception is reached
     * @since 0.0.14
     */

    T appendString(String string) throws IOException;

    default T append(Object object) throws IOException {
        if (object instanceof MarkdownElement) {
            return appendMarkdownElement((MarkdownElement) object);
        } else {
            return appendString(object.toString());
        }
    }

    T appendMarkdownElement(MarkdownElement element) throws IOException;

    @Deprecated
    default T appendBold(Object content) throws IOException {
        return appendMarkdownElement(new Bold(content));
    }

    @Deprecated
    default T appendCode(Object content) throws IOException {
        return appendMarkdownElement(new Code(content));
    }

    @Deprecated
    default T appendStrikethrough(Object content) throws IOException {
        return appendMarkdownElement(new Strikethrough(content));
    }

    @Deprecated
    default T appendItalic(Object content) throws IOException {
        return appendMarkdownElement(new Italic(content));
    }

    @Deprecated
    default T appendBulletList(Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(objects));
    }

    @Deprecated
    default T appendBulletList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(name, objects));
    }

    @Deprecated
    default T appendCodeBlock(Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(content));
    }

    @Deprecated
    default T appendCodeBlock(String language, Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(language, content));
    }

    @Deprecated
    default T appendHeader(Object content) throws IOException {
        return appendMarkdownElement(new Header(content));
    }

    @Deprecated
    default T appendHeader(int level, Object content) throws IOException {
        return appendMarkdownElement(new Header(level, content));
    }

    @Deprecated
    default T appendImage(String src) throws IOException {
        return appendMarkdownElement(new Image(src));
    }

    @Deprecated
    default T appendImage(String content, String src) throws IOException {
        return appendMarkdownElement(new Image(content, src));
    }

    @Deprecated
    default T appendLink(Object content, String url) throws IOException {
        return appendMarkdownElement(new Link(content, url));
    }

    @Deprecated
    default T appendLink(String url) throws IOException {
        return appendMarkdownElement(new Link(url));
    }

    @Deprecated
    default T appendNumberedList(Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(objects));
    }

    @Deprecated
    default T appendNumberedList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name, objects));
    }
}
