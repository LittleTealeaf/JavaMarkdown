package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.inline.Image;
import org.tealeaf.javamarkdown.inline.Link;
import org.tealeaf.javamarkdown.lists.BulletList;
import org.tealeaf.javamarkdown.lists.NumberedList;
import org.tealeaf.javamarkdown.markup.Bold;
import org.tealeaf.javamarkdown.markup.Code;
import org.tealeaf.javamarkdown.markup.Italic;
import org.tealeaf.javamarkdown.markup.Strikethrough;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class MarkdownBuilder extends Writer {

    private final Writer stringWriter;

    private char lastChar = '\u0000';

    public MarkdownBuilder() {
        super();
        this.stringWriter = new StringWriter();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        stringWriter.write(cbuf, off, len);
        lastChar = len > 0 ? cbuf[off + len - 1] : '\u0000';
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
        if(object instanceof MarkdownElement) {
            return appendMarkdownItem((MarkdownElement) object);
        } else {
            append(object.toString());
            return this;
        }
    }

    public MarkdownBuilder appendString(String string) throws IOException {
        return append((Object) string);
    }

    public MarkdownBuilder appendBold(Object object) throws IOException {
        return appendMarkdownItem(new Bold(object));
    }

    public MarkdownBuilder appendItalic(Object object) throws IOException {
        return appendMarkdownItem(new Italic(object));
    }

    public MarkdownBuilder appendStrikethrough(Object object) throws IOException {
        return appendMarkdownItem(new Strikethrough(object));
    }

    public MarkdownBuilder appendCode(Object object) throws IOException {
        return appendMarkdownItem(new Code(object));
    }

    public MarkdownBuilder appendLink(Object content, String href) throws IOException {
        return appendMarkdownItem(new Link(content,href));
    }

    public MarkdownBuilder appendImage(String url) throws IOException {
        return appendMarkdownItem(new Image(url));
    }

    public MarkdownBuilder appendBulletList(Object... items) throws IOException {
        return appendMarkdownItem(new BulletList(items));
    }

    public MarkdownBuilder appendNumberedList(Object... items) throws IOException {
        return appendMarkdownItem(new NumberedList(items));
    }


    protected MarkdownBuilder appendMarkdownItem(MarkdownElement markdownElement) throws IOException {
        if(markdownElement.requiresNewlineBefore() && lastChar != '\n') {
            write("\n");
        }
        markdownElement.toWriter(this);
        if(markdownElement.requiresNewlineAfter()) {
            write("\n");
        }

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
