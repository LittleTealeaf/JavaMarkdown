package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

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

    default T appendBold(Object content) throws IOException {
        return appendMarkdownElement(new Bold(content));
    }

    default T appendBulletList(Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(objects));
    }

    default T appendBulletList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(name,objects));
    }


    default T appendCode(Object content) throws IOException {
        return appendMarkdownElement(new Code(content));
    }

    default T appendCodeBlock(Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(content));
    }

    default T appendCodeBlock(String language, Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(language,content));
    }

    default T appendHeader(Object content) throws IOException {
        return appendMarkdownElement(new Header(content));
    }

    default T appendHeader(int level, Object content) throws IOException {
        return appendMarkdownElement(new Header(level,content));
    }
    default T appendImage(String src) throws IOException {
        return appendMarkdownElement(new Image(src));
    }

    default T appendImage(String content, String src) throws IOException {
        return appendMarkdownElement(new Image(content,src));
    }

    default T appendStrikethrough(Object content) throws IOException {
        return appendMarkdownElement(new Strikethrough(content));
    }

    default T appendItalic(Object content) throws IOException {
        return appendMarkdownElement(new Italic(content));
    }


    default T appendLink(Object content, String url) throws IOException {
        return appendMarkdownElement(new Link(content, url));
    }

    default T appendLink(String url) throws IOException {
        return appendMarkdownElement(new Link(url));
    }

    default T appendNumberedList(Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(objects));
    }

    default T appendNumberedList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name,objects));
    }

    default T appendNumberedList(int start, Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(start,objects));
    }

    default T appendNumberedList(String name, int start, Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name,start,objects));
    }

    default T appendNumberedList(List<?> objects) throws IOException {
        return appendMarkdownElement(new NumberedList(objects));
    }

    default T appendNumberedList(Stream<?> stream) throws IOException {
        return appendMarkdownElement(new NumberedList(stream));
    }

    default T appendNumberedList(int start, List<?> objects) throws IOException {
        return appendMarkdownElement(new NumberedList(start,objects));
    }

    default T appendNumberedList(int start, Stream<?> stream) throws IOException {
        return appendMarkdownElement(new NumberedList(start,stream));
   }

   default T appendNumberedList(String name, List<?> objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name,objects));
   }

   default T appendNumberedList(String name, Stream<?> stream) throws IOException {
        return appendMarkdownElement(new NumberedList(name,stream));
   }

   default T appendNumberedList(String name, int start, List<?> objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name,start,objects));
   }

   default T appendNumberedList(String name, int start, Stream<?> stream) throws IOException {
        return appendMarkdownElement(new NumberedList(name,start,stream));
   }
}
