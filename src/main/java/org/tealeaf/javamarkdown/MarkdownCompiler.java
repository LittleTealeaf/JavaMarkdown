package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;

/**
 * @param <T> The class type to return in append methods
 *
 * @author Thomas Kwashnak
 * @since 0.0.13
 */
public interface MarkdownCompiler<T extends MarkdownCompiler<?>> {

    T appendString(String string) throws IOException;

    /**
     * <p>Writes an object to the writer</p>
     * <p>If the object is an instance of {@link MarkdownElement}, then {@link #appendMarkdownElement(MarkdownElement)} will instead be used to handle additional required
     * functionality. Otherwise, {@link Object#toString()} will be used to print the object</p>
     *
     * @param object Object to print to the writer
     *
     * @return A reference to this Writer
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.7
     */
    default T append(Object object) throws IOException {
        if (object instanceof MarkdownElement) {
            return appendMarkdownElement((MarkdownElement) object);
        } else {
            return appendString(object.toString());
        }
    }

    /**
     * <p>Adds a {@link MarkdownElement} to the writer, handling any special conditions</p>
     * <p>If {@link MarkdownElement#requiresNewlineBefore() markdownElement.requiresNewlineBefore()} returns true, and the last character written was not a <code>\n</code>,
     * then a <code>\n</code> will be written before writing the element. </p>
     * <p>If {@link MarkdownElement#requiresNewlineAfter() markdownElement.requiresNewlineAfter()} returns true, then a <code>\n</code> will be printed after the elemented
     * is written.
     * </p>
     *
     * @param element The element to write to the writer
     *
     * @return An instance of this writer
     *
     * @throws IOException If an I/O error occurs
     */
    T appendMarkdownElement(MarkdownElement element) throws IOException;

    default T appendBold(Object content) throws IOException {
        return appendMarkdownElement(new Bold(content));
    }

    default T appendCode(Object content) throws IOException {
        return appendMarkdownElement(new Code(content));
    }

    default T appendStrikethrough(Object content) throws IOException {
        return appendMarkdownElement(new Strikethrough(content));
    }

    default T appendItalic(Object content) throws IOException {
        return appendMarkdownElement(new Italic(content));
    }

    /**
     * <p>Inserts a bullet list of objects into the writer.</p>
     * <p>Uses the form:</p>
     * <pre>
     *     - Item 1
     *     - Item 2
     *     - Item 3
     *       Item 3, 2nd line
     * </pre>
     * <p>to render as a bullet list in markdown</p>
     *
     * @param objects Array of items to put on each line
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     */
    default T appendBulletList(Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(objects));
    }

    default T appendBulletList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new BulletList(name, objects));
    }

    /**
     * <p>Insert a code block with the provided code content into a writer</p>
     *
     * @param content Content to put in the code block
     *
     * @return A reference to the writer
     *
     * @throws IOException If an I/O error occurs
     */
    default T appendCodeBlock(Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(content));
    }

    /**
     * <p>Insert a code block with the provided content and a given language into a writer</p>
     *
     * @param language Name of the language to render in
     * @param content  Content to put in the code block
     *
     * @return A reference to the writer
     *
     * @throws IOException If an I/O error occurs
     */
    default T appendCodeBlock(String language, Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(language, content));
    }

    default T appendHeader(Object content) throws IOException {
        return appendMarkdownElement(new Header(content));
    }

    default T appendHeader(int level, Object content) throws IOException {
        return appendMarkdownElement(new Header(level, content));
    }

    default T appendImage(String src) throws IOException {
        return appendMarkdownElement(new Image(src));
    }

    default T appendImage(String content, String src) throws IOException {
        return appendMarkdownElement(new Image(content, src));
    }

    default T appendLink(Object content, String url) throws IOException {
        return appendMarkdownElement(new Link(content, url));
    }

    default T appendLink(String url) throws IOException {
        return appendMarkdownElement(new Link(url));
    }

    /**
     * <p>Inserts a numbered list of objects into the writer.</p>
     * <p>Uses the form:</p>
     * <pre>
     *     1. Item 1
     *     1. Item 2
     *     1. Item 3
     *       Item 3, 2nd line
     * </pre>
     * <p>to render as a bullet list in markdown</p>
     *
     * @param objects Array of items to put on each line
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     */
    default T appendNumberedList(Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(objects));
    }

    default T appendNumberedList(String name, Object[] objects) throws IOException {
        return appendMarkdownElement(new NumberedList(name, objects));
    }
}
