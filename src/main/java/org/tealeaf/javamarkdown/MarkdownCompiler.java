package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>Implements methods used within any markdown compiler. Provides many shortcut methods for each markdown element that can be appended</p>
 * @param <T> The class type to return in append methods. This should be the class inheriting this interface
 *
 * @author Thomas Kwashnak
 * @since 0.0.14
 */
public interface MarkdownCompiler<T extends MarkdownCompiler<?>> {

    /**
     * <p>Appends a string to the end of the document.</p>
     * @param string String to append to the end of the document
     * @return An instance of the MarkdownCompiler used
     * @throws IOException if an I/O exception is raised.
     * @since 0.0.14
     */
    T appendString(String string) throws IOException;

    /**
     * <p>Appends any given object to the end of the document. Checks what type of object was passed and uses the correct method accordingly</p>
     * <ul>
     *     <li>If the object provided extends the {@link MarkdownElement} class, then this method will use {@link #appendMarkdownElement(MarkdownElement)} to handle adding
     *     the element to the document.</li>
     *     <li>Otherwise, this method will use {@link #appendString(String)} and pass the {@link Object#toString()} result as the string.</li>
     * </ul>
     * @param object Content to add to the end of the document
     * @return An instance of the MarkdownCompiler used
     * @throws IOException if an I/O exception is raised
     * @see #appendString(String)
     * @see #appendMarkdownElement(MarkdownElement)
     * @since 0.0.14
     */
    default T append(Object object) throws IOException {
        return object instanceof MarkdownElement ? appendMarkdownElement((MarkdownElement) object) : appendString(object.toString());
    }

    /**
     * <p>Appends a markdown element to the end of the document. Adjusts and manages new lines depending on the requirements set by the Markdown Element</p>
     * @param element Markdown Element to add to the end of the document
     * @return An instance of the MarkdownCompiler used
     * @throws IOException if an I/O exception is raised
     * @see MarkdownElement#requiresNewlineBefore()
     * @see MarkdownElement#requiresNewlineAfter()
     * @since 0.0.14
     */
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

    default T appendBulletList(List<?> objects) throws IOException {
        return appendMarkdownElement(new BulletList(objects));
    }

    default T appendBulletList(Stream<?> stream) throws IOException {
        return appendMarkdownElement(new BulletList(stream));
    }

    default T appendBulletList(String name, List<?> objects) throws IOException {
        return appendMarkdownElement(new BulletList(name,objects));
    }

    default T appendBulletList(String name, Stream<?> stream) throws IOException {
        return appendMarkdownElement(new BulletList(name,stream));
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
