package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tealeaf.javamarkdown.elements.*;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownWriterTest {

    MarkdownWriter markdownWriter;

    @BeforeEach
    void setup() {
        markdownWriter = new MarkdownWriter();
    }

    @Test
    void emptyConstructor() {
        markdownWriter = new MarkdownWriter();
        assertNotNull(markdownWriter.getWriter());

    }
    @Test
    void presetConstructor() {
        Writer writer = new StringWriter();
        markdownWriter = new MarkdownWriter(writer);
        assertSame(writer,markdownWriter.getWriter());
    }

    @Test
    void write() throws IOException {
        String word = Tests.randomWord();
        markdownWriter.write(word);
        assertEquals(word,markdownWriter.getWriter().toString());
    }

    @Test
    void getLastChar() throws IOException {
        String word = Tests.randomWord();
        assertEquals(markdownWriter.getLastChar(),'\u0000');
        markdownWriter.write(word);
        assertEquals(word.charAt(word.length() - 1),markdownWriter.getLastChar());
    }

    @Test
    void flush() throws IOException {

    }

    @Test
    void close() throws IOException {

    }

    @Test
    void appendString() throws IOException {
        String word = Tests.randomWord();
        assertSame(markdownWriter,markdownWriter.appendString(word));
        assertEquals(word,markdownWriter.toString());
    }

    @Test
    void appendMarkdownElement() throws IOException {
        Bold bold = Tests.bold();
        assertSame(markdownWriter,markdownWriter.appendMarkdownElement(bold));
        assertEquals(bold.toString(),markdownWriter.toString());
    }

    @Test
    void appendMarkdownElementLineBefore() throws IOException {
        Header header = Tests.header();
        assertSame(markdownWriter,markdownWriter.appendMarkdownElement(header));
        assertEquals("\n" + header.toString() + "\n",markdownWriter.toString());
        markdownWriter.appendMarkdownElement(header);
        assertEquals("\n" + header.toString() + "\n" + header + "\n",markdownWriter.toString());
    }

    @Test
    void appendMarkdownElementLineAfter() throws IOException {
        Header header = Tests.header();
        assertSame(markdownWriter,markdownWriter.appendMarkdownElement(header));
        assertEquals('\n',markdownWriter.getLastChar());
    }


    @Test
    void getWriter() {
        assertNotNull(markdownWriter.getWriter());

        StringWriter writer = new StringWriter();
        markdownWriter = new MarkdownWriter(writer);
        assertEquals(writer,markdownWriter.getWriter());


    }

    @Test
    void testToString() throws IOException {
        Writer writer = new StringWriter();
        writer.write("test");
        markdownWriter = new MarkdownWriter(writer);
        assertEquals(writer.toString(),markdownWriter.toString());
    }
}
