package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * Implements methods used within any markdown compiler. Provides many shortcut
 * methods for each markdown element that can be appended
 * </p>
 *
 * @param <T> The class type to return in append methods. This should be the
 *            class inheriting this interface
 *
 * @author Thomas Kwashnak
 * @since 0.0.14
 */
public interface MarkdownCompiler<T extends MarkdownCompiler<?>> {

    /**
     * <p>
     * Appends any given object to the end of the document. Checks what type of
     * object was passed and uses the correct method accordingly
     * </p>
     * <ul>
     * <li>If the object provided extends the {@link MarkdownElement} class, then
     * this method will use {@link #appendMarkdownElement(MarkdownElement)} to
     * handle adding
     * the element to the document.</li>
     * <li>Otherwise, this method will use {@link #appendString(String)} and pass
     * the {@link Object#toString()} result as the string.</li>
     * </ul>
     *
     * @param object Content to add to the end of the document
     *
     * @return An instance of the MarkdownCompiler used
     *
     * @see #appendString(String)
     * @see #appendMarkdownElement(MarkdownElement)
     * @since 0.0.14
     */
    default T append(Object object) {
        return object instanceof MarkdownElement ? appendMarkdownElement((MarkdownElement) object)
                : appendString(object.toString());
    }

    /**
     * <p>
     * Appends a markdown element to the end of the document. Adjusts and manages
     * new lines depending on the requirements set by the Markdown Element
     * </p>
     *
     * @param element Markdown Element to add to the end of the document
     *
     * @return An instance of the MarkdownCompiler used
     *
     * @see MarkdownElement#requiresNewlineBefore()
     * @see MarkdownElement#requiresNewlineAfter()
     * @since 0.0.14
     */
    T appendMarkdownElement(MarkdownElement element);

    /**
     * <p>
     * Appends a string to the end of the document.
     * </p>
     *
     * @param string String to append to the end of the document
     *
     * @return An instance of the MarkdownCompiler used
     *
     * @since 0.0.14
     */
    T appendString(String string);

    /**
     * <p>
     * Displays provided content as <b>bold</b>.
     * </p>
     * <p>
     * Wraps the content provided in <code>~</code>
     * </p>
     *
     * @param content The content to be displayed in bold
     *
     * @return This MarkdownCompiler
     *
     * @since 0.0.15
     */
    default T appendBold(Object content) {
        return appendMarkdownElement(new Bold(content));
    }

    /**
     * @since 0.0.15
     */
    default T appendBulletList(Object[] objects) {
        return appendMarkdownElement(new BulletList(objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendBulletList(String name, Object[] objects) {
        return appendMarkdownElement(new BulletList(name, objects));
    }

    default T appendBulletList(List<?> objects) {
        return appendMarkdownElement(new BulletList(objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendBulletList(Stream<?> stream) {
        return appendMarkdownElement(new BulletList(stream));
    }

    /**
     * @since 0.0.15
     */
    default T appendBulletList(String name, List<?> objects) {
        return appendMarkdownElement(new BulletList(name, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendBulletList(String name, Stream<?> stream) {
        return appendMarkdownElement(new BulletList(name, stream));
    }

    /**
     * <p>
     * Displays provided content as short code snippets
     * </p>
     * <p>
     * These code snippets are typically displayed in a monospace font and have a
     * grey background
     * </p>
     * <p>
     * This will throw an
     * {@link org.tealeaf.javamarkdown.exceptions.IllegalContentsException} if a
     * {@link org.tealeaf.javamarkdown.MarkdownElement} is passed in. If you really
     * want to pass a {@code MarkdownElement} in, you can simply just use its
     * {@code toString()} method to convert it to a String first.
     * </p>
     *
     * @since 0.0.15
     * @param content The content to display within the code snippet.
     * @return This Compiler
     */
    default T appendCode(Object content) {
        return appendMarkdownElement(new Code(content));
    }

    /**
     * <p>Displays a block of code</p>
     * <p>These code blocks are typically displayed in a monospace font and have a grey background</p>
     *
     * @since 0.0.15
     * @param content The code to display in the code snippet
     * @return This Compiler
     */
    default T appendCodeBlock(Object content) {
        return appendMarkdownElement(new CodeBlock(content));
    }

    /**
     * <p>Displays a block of code with syntax highlighting</p>
     * <p>These code blocks are typically displayed in a monospaced font and have a grey background. Indentation is also displayed</p>
     * @param language The syntax highlighting language to use
     * @param content The code to display in the code snippet
     * @return This Compiler
     * @since 0.0.15
     */
    default T appendCodeBlock(String language, Object content) {
        return appendMarkdownElement(new CodeBlock(language, content));
    }

    /*
     *
     * @since 0.0.15
     */
    default T appendHeader(Object content) {
        return appendMarkdownElement(new Header(content));
    }

    /**
     * @since 0.0.15
     */
    default T appendHeader(int level, Object content) {
        return appendMarkdownElement(new Header(level, content));
    }

    /**
     * @since 0.0.15
     */
    default T appendImage(String src) {
        return appendMarkdownElement(new Image(src));
    }

    /**
     * @since 0.0.15
     */
    default T appendImage(String content, String src) {
        return appendMarkdownElement(new Image(content, src));
    }

    /**
     * <p>Appends content formatted as <span style="text-decoration:line-through;">struck out</span></p>
     * @param content Content to strike out
     * @return This instance of the compiler
     * @since 0.0.15
     */
    default T appendStrikethrough(Object content) {
        return appendMarkdownElement(new Strikethrough(content));
    }

    /**
     * <p>
     * Appends text to the end of the compiler formatted as <i>italics</i>
     * </p>
     *
     * @param content The content you wish to be formatted in italics
     * @since 0.0.15
     */
    default T appendItalic(Object content) {
        return appendMarkdownElement(new Italic(content));
    }

    /**
     * @since 0.0.15
     */
    default T appendLink(Object content, String url) {
        return appendMarkdownElement(new Link(content, url));
    }

    /**
     * @since 0.0.15
     */
    default T appendLink(String url) {
        return appendMarkdownElement(new Link(url));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(Object[] objects) {
        return appendMarkdownElement(new NumberedList(objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, Object[] objects) {
        return appendMarkdownElement(new NumberedList(name, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(int start, Object[] objects) {
        return appendMarkdownElement(new NumberedList(start, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, int start, Object[] objects) {
        return appendMarkdownElement(new NumberedList(name, start, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(List<?> objects) {
        return appendMarkdownElement(new NumberedList(objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(Stream<?> stream) {
        return appendMarkdownElement(new NumberedList(stream));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(int start, List<?> objects) {
        return appendMarkdownElement(new NumberedList(start, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(int start, Stream<?> stream) {
        return appendMarkdownElement(new NumberedList(start, stream));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, List<?> objects) {
        return appendMarkdownElement(new NumberedList(name, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, Stream<?> stream) {
        return appendMarkdownElement(new NumberedList(name, stream));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, int start, List<?> objects) {
        return appendMarkdownElement(new NumberedList(name, start, objects));
    }

    /**
     * @since 0.0.15
     */
    default T appendNumberedList(String name, int start, Stream<?> stream) {
        return appendMarkdownElement(new NumberedList(name, start, stream));
    }

    /**
     * @since 0.0.15
     */
    default T appendTable(Object[] headers, Object[][] content) {
        return appendMarkdownElement(new Table().setHeaders(headers).addRows(content));
    }

    /**
     * @since 0.0.15
     */
    default T appendTable(Object[] headers, Table.Alignment[] alignments, Object[][] content) {
        return appendMarkdownElement(new Table().setHeaders(headers).setAlignments(alignments).addRows(content));
    }
}
