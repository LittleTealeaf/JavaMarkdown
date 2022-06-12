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
    private final LinkedList<Object> items = new LinkedList<>();

    @Override
    public MarkdownBuffer appendString(String string) {
        items.add(string);

        return this;
    }

    @Override
    public MarkdownBuffer appendMarkdownElement(MarkdownElement element) {
        items.add(element);
        return this;
    }

    public <T extends Writer> T toWriter(T writer) {
        boolean newLine = true;
        for(Object item : items) {
            try {
                if(!newLine && item instanceof MarkdownElement && ((MarkdownElement) item).requiresNewlineBefore()) {
                    writer.write('\n');
                }
                writer.write(item.toString());
                newLine = false;
                if(item instanceof MarkdownElement && ((MarkdownElement) item).requiresNewlineAfter()) {
                    writer.write('\n');
                    newLine = true;
                }
            } catch(IOException ignored) {}
        }
        return writer;
    }

    @Override
    public String toString() {
        return toWriter(new StringWriter()).toString();
    }
}
