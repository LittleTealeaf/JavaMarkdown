package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.markup.Bold;
import org.tealeaf.javamarkdown.markup.Code;
import org.tealeaf.javamarkdown.markup.Italic;
import org.tealeaf.javamarkdown.markup.Strikethrough;

import java.io.IOException;
import java.io.StringWriter;

public class MarkdownBuilder {

    private final StringWriter stringWriter;

    public MarkdownBuilder() {
        this.stringWriter = new StringWriter();
    }

    public MarkdownBuilder(StringWriter stringWriter) {
        this.stringWriter = stringWriter;
    }

    public MarkdownBuilder append(Object object) {
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
        new Italic(object).toWriter(stringWriter);
        return this;
    }

    public MarkdownBuilder appendCode(Object object) throws IOException {
        new Code(object).toWriter(stringWriter);
        return this;
    }

    public StringWriter getStringWriter() {
        return stringWriter;
    }

    @Override
    public String toString() {
        return stringWriter.toString();
    }
}
