package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;

/**
 *
 * @param <T> The class type to return in append methods
 * @since 0.0.13
 * @author Thomas Kwashnak
 */
public interface MarkdownCompiler <T extends MarkdownCompiler<?>> {
    T appendString(String string)throws IOException;
    T appendMarkdownElement(MarkdownElement element)throws IOException;
    default T appendBold(Object content) throws IOException {
        return appendMarkdownElement(new Bold(content));
    }
    default T appendCode(Object content)throws IOException {
        return appendMarkdownElement(new Code(content));
    }
    default T appendStrikethrough(Object content)throws IOException {
        return appendMarkdownElement(new Strikethrough(content));
    }
    default T appendItalic(Object content)throws IOException {
        return appendMarkdownElement(new Italic(content));
    }

    default T appendBulletList(Object[] objects)throws IOException {
        return appendMarkdownElement(new BulletList(objects));
    }

    default T appendBulletList(String name, Object[] objects)throws IOException {
        return appendMarkdownElement(new BulletList(name,objects));
    }

    default T appendCodeBlock(Object content)throws IOException {
        return appendMarkdownElement(new CodeBlock(content));
    }

    default T appendCodeBlock(String language, Object content)throws IOException {
        return appendMarkdownElement(new CodeBlock(language,content));
    }

    default T appendHeader(Object content)throws IOException {
        return appendMarkdownElement(new Header(content));
    }

    default T appendHeader(int level, Object content)throws IOException {
        return appendMarkdownElement(new Header(level,content));
    }

    default T appendImage(String src)throws IOException {
        return appendMarkdownElement(new Image(src));
    }

    default T appendImage(String content, String src)throws IOException {
        return appendMarkdownElement(new Image(content,src));
    }

    default T appendLink(Object content, String url)throws IOException {
        return appendMarkdownElement(new Link(content,url));
    }

    default T appendLink(String url)throws IOException {
        return appendMarkdownElement(new Link(url));
    }

    default T appendNumberedList(Object[] objects)throws IOException {
        return appendMarkdownElement(new NumberedList(objects));
    }

    default T appendNumberedList(String name, Object[] objects)throws IOException {
        return appendMarkdownElement(new NumberedList(name,objects));
    }

}
