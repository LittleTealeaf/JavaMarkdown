package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.markup.Bold;
import org.tealeaf.javamarkdown.markup.Code;
import org.tealeaf.javamarkdown.markup.Italic;
import org.tealeaf.javamarkdown.markup.Strikethrough;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class MarkdownBuilder extends Writer {

    private final Writer stringWriter;

    public MarkdownBuilder() {
        super();
        this.stringWriter = new StringWriter();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        stringWriter.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        stringWriter.flush();
    }

    @Override
    public void close() throws IOException {
        stringWriter.close();
    }

    public MarkdownBuilder(Writer stringWriter) {
        this.stringWriter = stringWriter;
    }

    public MarkdownBuilder append(Object object) throws IOException {
        stringWriter.append(object.toString());
        return this;
    }

    public MarkdownBuilder appendBold(Object object) throws IOException {
        new Bold(object).toWriter(stringWriter);
        return this;
    }

    public MarkdownBuilder appendItalic(Object object) throws IOException {
        new Italic(object).toWriter(stringWriter);
        return this;
    }

    public MarkdownBuilder appendStrikethrough(Object object) throws IOException {
        new Strikethrough(object).toWriter(stringWriter);
        return this;
    }

    public MarkdownBuilder appendCode(Object object) throws IOException {
        new Code(object).toWriter(stringWriter);
        return this;
    }

    public Writer getWriter() {
        return stringWriter;
    }

    @Override
    public String toString() {
        return stringWriter.toString();
    }
}
