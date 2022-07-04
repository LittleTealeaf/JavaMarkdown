package org.tealeaf.javamarkdown;

//Change to @since 0.0.12 if this is released before 0.0.12

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;

/**
 * @author Thomas Kwashnak
 * @since 0.0.14
 */
public class MarkdownBuffer implements MarkdownCompiler<MarkdownBuffer> {

    protected final LinkedList<Object> items = new LinkedList<>();

    @Override
    public MarkdownBuffer appendString(String string) {
        items.add(string);

        return this;
    }

    @Override
    public MarkdownBuffer append(Object object) {
        if (object instanceof MarkdownBuffer) {
            return appendMarkdownBuffer((MarkdownBuffer) object);
        } else {
            return MarkdownCompiler.super.append(object);
        }
    }

    @Override
    public MarkdownBuffer appendMarkdownElement(MarkdownElement element) {
        items.add(element);
        return this;
    }

    /***
     * Unpacks another markdown buffer and appends each item to the end of the list
     * @param markdownBuffer MarkdownBuffer to unpack
     * @return A reference to the final Markdown Buffer
     * @since 0.0.15
     */
    public MarkdownBuffer appendMarkdownBuffer(MarkdownBuffer markdownBuffer) {
        markdownBuffer.items.forEach(this::append);
        return this;
    }

    @Override
    public String toString() {
        return toWriter(new StringWriter()).toString();
    }

    public <T extends Writer> T toWriter(T writer) {
        boolean newLine = true;
        for (Object item : items) {
            try {
                if (!newLine && item instanceof MarkdownElement && ((MarkdownElement) item).requiresNewlineBefore()) {
                    writer.write('\n');
                }
                writer.write(item.toString());
                newLine = false;
                if (item instanceof MarkdownElement && ((MarkdownElement) item).requiresNewlineAfter()) {
                    writer.write('\n');
                    newLine = true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return writer;
    }
}
