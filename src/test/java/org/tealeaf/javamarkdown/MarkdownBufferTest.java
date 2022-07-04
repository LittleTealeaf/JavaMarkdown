package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.Code;
import org.tealeaf.javamarkdown.elements.Header;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownBufferTest {

    MarkdownBuffer markdownBuffer;

    @BeforeEach
    void setup() {
        markdownBuffer = new MarkdownBuffer();
    }

    @Test
    void appendStringReturnsItself() {
        assertSame(markdownBuffer, markdownBuffer.appendString("test"));
    }

    @Test
    void appendMarkdownElementReturnsItself() {
        assertSame(markdownBuffer, markdownBuffer.appendMarkdownElement(new Code("test")));
    }

    @Test
    void toStringUsesToWriter() throws IOException {
        StringWriter stringWriter = new StringWriter();
        markdownBuffer.appendString("test string");
        markdownBuffer.appendCode("test code");
        markdownBuffer.appendHeader("header");
        assertEquals(markdownBuffer.toString(), markdownBuffer.toWriter(stringWriter).toString());
    }

    @Test
    void requiredLineDoesNotPrintWhenFirst() throws IOException {
        markdownBuffer.appendHeader("test");
        assertStringEquals(new Header("test") + "\n");
    }

    void assertStringEquals(String text) {
        assertEquals(text, markdownBuffer.toString());
    }

    @Test
    void insertsSingleNewlineBetweenStructures() throws IOException {
        markdownBuffer.appendHeader("a");
        markdownBuffer.appendHeader("b");
        assertStringEquals(new Header("a") + "\n" + new Header("b") + "\n");
    }

    @Test
    void usesSuperMarkdownAppendByDefault() throws IOException {
        String string = Tests.randomWord();
        markdownBuffer.append(string);
        assertEquals(string, markdownBuffer.items.get(0));

        markdownBuffer.appendHeader(Tests.randomSentence());
        assertTrue(markdownBuffer.items.get(1) instanceof Header);
    }

    @Test
    void appendingMarkdownBuffersUnpacksLists() throws IOException {
        MarkdownBuffer buffer = new MarkdownBuffer();
        buffer.appendHeader(Tests.randomSentence());
        buffer.appendCode("tests");
        buffer.appendItalic("testing");

        markdownBuffer.append(buffer);
        assertNotEquals(1, markdownBuffer.items.size());
    }

    @Test
    void appendMarkdownBuffer() throws IOException {
        MarkdownBuffer buffer = new MarkdownBuffer();
        buffer.appendHeader(Tests.randomSentence());
        buffer.appendCode("tests");
        buffer.appendItalic("testing");

        markdownBuffer.appendMarkdownBuffer(buffer);
        assertNotEquals(1, markdownBuffer.items.size());
    }
}