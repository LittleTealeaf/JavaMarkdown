package org.tealeaf.javamarkdown;

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
        super();
        this.writer = new StringWriter();
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
        writer.write(cbuf, off, len);
        lastChar = len > 0 ? cbuf[off + len - 1] : '\u0000';
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

//    /**
//     * <p>Writes an object to the writer</p>
//     * <p>If the object is an instance of {@link MarkdownElement}, then {@link #appendMarkdownElement(MarkdownElement)} will instead be used to handle additional required
//     * functionality. Otherwise, {@link Object#toString()} will be used to print the object</p>
//     *
//     * @param object Object to print to the writer
//     *
//     * @return A reference to this Writer
//     *
//     * @throws IOException If an I/O error occurs
//     * @since 0.0.7
//     */
//    public MarkdownWriter append(Object object) throws IOException {
//        if (object instanceof MarkdownElement) {
//            return appendMarkdownElement((MarkdownElement) object);
//        } else {
//            append(object.toString()); return this;
//        }
//    }

    /**
     * <p>Writes a string to the writer</p>
     * <p>This method allows the forcing of writing a String that would otherwise be using {@link #append(CharSequence)}, which returns a {@link Writer} instead of a
     * {@link MarkdownWriter}.</p>
     *
     * @param string String to print to the writer
     *
     * @return A reference to this Writer
     *
     * @since 0.0.9
     */
    public MarkdownWriter appendString(String string) {
        try {
            write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * @since 0.0.9
     */
    public MarkdownWriter appendMarkdownElement(MarkdownElement markdownElement) {
        try {
            if (markdownElement.requiresNewlineBefore() && lastChar != '\n') {
                write("\n");
            }
            markdownElement.toWriter(this);
            if (markdownElement.requiresNewlineAfter()) {
                write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public char getLastChar() {
        return lastChar;
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}

