package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <p>
 * A builder, or writer, that implements methods for appending markdown elements, as well as the ability to directly append markdown elements
 * </p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.9
 */
public class MarkdownWriter extends Writer implements MarkdownCompiler<MarkdownWriter> {

    private final Writer writer;

    private char lastChar = '\u0000';

    /**
     * Creates a new {@code MarkdownWriter}, using a new {@link StringWriter} to handle the writing functionality.
     *
     * @since 0.0.7
     */
    public MarkdownWriter() {
        super(); this.writer = new StringWriter();
    }

    /**
     * {@inheritDoc}
     * <p>Updates the last written character value for use in {@link #appendMarkdownElement(MarkdownElement)}</p>
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.7
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len); lastChar = len > 0 ? cbuf[off + len - 1] : '\u0000';
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.7
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.7
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }

    /**
     * <p>Creates a new MarkdownBuilder with a preset {@link Writer}. The {@code Writer} is used as the back-end functionality. Any appending method used will append the
     * proper string to the {@code Writer}</p>
     *
     * @param writer Writer to write objects to
     *
     * @since 0.0.8
     */
    public MarkdownWriter(Writer writer) {
        this.writer = writer;
    }

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
    public MarkdownWriter append(Object object) throws IOException {
        if (object instanceof MarkdownElement) {
            return appendMarkdownElement((MarkdownElement) object);
        } else {
            append(object.toString()); return this;
        }
    }

    /**
     * <p>Writes a string to the writer</p>
     * <p>This method allows the forcing of writing a String that would otherwise be using {@link #append(CharSequence)}, which returns a {@link Writer} instead of a
     * {@link MarkdownWriter}.</p>
     *
     * @param string String to print to the writer
     *
     * @return A reference to this Writer
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.9
     */
    public MarkdownWriter appendString(String string) throws IOException {
        return append((Object) string);
    }

    /**
     * <p>Writes text to the writer as <b>Bold Text</b></p>
     * <p>Uses the <code>**Text**</code> syntax to render the text as bold in Markdown</p>
     *
     * @param object The object to be rendered in bold
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @see Bold
     * @since 0.0.7
     */
    public MarkdownWriter appendBold(Object object) throws IOException {
        return appendMarkdownElement(new Bold(object));
    }

    /**
     * <p>Writes text to the writer as <i>Italicized Text</i></p>
     * <p>Uses the <code>*Text*</code> syntax to render the text as italicized in Markdown</p>
     *
     * @param object The object to render in italics
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @see Italic
     * @since 0.0.7
     */
    public MarkdownWriter appendItalic(Object object) throws IOException {
        return appendMarkdownElement(new Italic(object));
    }

    /**
     * <p>Writes text to the writer as <s>Struck Out Text</s></p>
     * <p>Uses the <code>~~Text~~</code> syntax to render the text as struck out in Markdown</p>
     *
     * @param object Object to render struck out
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @see Strikethrough
     * @since 0.0.7
     */
    public MarkdownWriter appendStrikethrough(Object object) throws IOException {
        return appendMarkdownElement(new Strikethrough(object));
    }

    /**
     * <p>Writes text to the writer as a <code style="background-color: 383e47; border-radius: 6px; padding: 2px 4px;">Code Snippet</code></p>
     * <p>Uses the <code>`Text`</code> syntax to render the text as a code snippet in Markdown</p>
     *
     * @param object Object to be rendered in a Code Snippet
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @see Code
     * @since 0.0.7
     */
    public MarkdownWriter appendCode(Object object) throws IOException {
        return appendMarkdownElement(new Code(object));
    }

    /**
     * <p>Inserts a link into the writer</p>
     * <p>Uses the <code>[content](url)</code> syntax to insert a link in Markdown</p>
     *
     * @param content The content to display in the link.
     * @param href    The url that the link should direct to
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.9
     */
    public MarkdownWriter appendLink(Object content, String href) throws IOException {
        return appendMarkdownElement(new Link(content, href));
    }

    /**
     * <p>Inserts an image into the writer</p>
     * <p>Uses the <code>![](url)</code> syntax to insert an image in Markdown</p>
     *
     * @param url The source url of the image
     *
     * @return A reference to this writer
     *
     * @throws IOException If an I/O error occurs
     * @since 0.0.9
     */
    public MarkdownWriter appendImage(String desc, String url) throws IOException {
        return appendMarkdownElement(new Image(desc, url));
    }

    /**
     * @since 0.0.9
     */
    public MarkdownWriter appendBulletList(Object... items) throws IOException {
        return appendMarkdownElement(new BulletList(items));
    }

    /**
     * @since 0.0.9
     */
    public MarkdownWriter appendNumberedList(Object... items) throws IOException {
        return appendMarkdownElement(new NumberedList(items));
    }

    public MarkdownWriter appendHeader(Object content) throws IOException {
        return appendMarkdownElement(new Header(content));
    }

    public MarkdownWriter appendHeader(int level, Object content) throws IOException {
        return appendMarkdownElement(new Header(level, content));
    }

    /**
     * @since 0.0.12
     */
    public MarkdownWriter appendCodeBlock(Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(content));
    }

    /**
     * @since 0.0.12
     */
    public MarkdownWriter appendCodeBlock(String language, Object content) throws IOException {
        return appendMarkdownElement(new CodeBlock(language, content));
    }


    /**
     * @since 0.0.9
     */
    public MarkdownWriter appendMarkdownElement(MarkdownElement markdownElement) throws IOException {
        if (markdownElement.requiresNewlineBefore() && lastChar != '\n') {
            write("\n");
        } markdownElement.toWriter(this); if (markdownElement.requiresNewlineAfter()) {
            write("\n");
        }

        return this;
    }

    /**
     * Gets the writer used to handle core functionality
     *
     * @return The writer used for functionality
     *
     * @since 0.0.9
     */
    public Writer getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}

