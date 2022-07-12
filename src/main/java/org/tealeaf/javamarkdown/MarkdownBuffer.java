package org.tealeaf.javamarkdown;

//Change to @since 0.0.12 if this is released before 0.0.12

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;

/**
 * A MarkdownCompiler that first takes all objects passed in and compiles it to an array, only rendering them when requested.
 * @author Thomas Kwashnak
 * @since 0.0.14
 */
public class MarkdownBuffer implements MarkdownCompiler<MarkdownBuffer> {

    protected final LinkedList<Object> items = new LinkedList<>();

    /**
     * {@inheritDoc}
     * @since 0.0.14
     */
    @Override
    public MarkdownBuffer appendString(String string) {
        items.add(string);

        return this;
    }

    /**
     * {@inheritDoc}
     * <pre>If another {@code MarkdownBuffer} object is passed, this buffer will use {@link #appendMarkdownBuffer(MarkdownBuffer)} to append its contents</pre>
     * @since 0.0.15
     */
    @Override
    public MarkdownBuffer append(Object object) {
        if (object instanceof MarkdownBuffer) {
            return appendMarkdownBuffer((MarkdownBuffer) object);
        } else {
            return MarkdownCompiler.super.append(object);
        }
    }

    /**
     * {@inheritDoc}
     * @since 0.0.14
     */
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


    /**
     * {@inheritDoc}
     * @since 0.0.14
     */
    @Override
    public String toString() {
        return toWriter(new StringWriter()).toString();
    }

    /**
     * Attempts to render and write all objects to a {@link Writer}.
     * @param <T> The writer's original type, which should extend the {@link Writer} class
     * @param writer The writer itself
     * @return The writer after attempting to write the objects to the writer
     * @since 0.0.14
     */
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
