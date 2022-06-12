package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.Code;
import org.tealeaf.javamarkdown.elements.Header;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownBufferTest {

    MarkdownBuffer markdownBuffer;

    void assertStringEquals(String text) {
        assertEquals(text,markdownBuffer.toString());
    }

    @BeforeEach
    void setup() {
        markdownBuffer = new MarkdownBuffer();
    }

    @Test
    void appendStringReturnsItself() {
        assertSame(markdownBuffer,markdownBuffer.appendString("test"));
    }

    @Test
    void appendMarkdownElementReturnsItself() {
        assertSame(markdownBuffer,markdownBuffer.appendMarkdownElement(new Code("test")));
    }

    @Test
    void toStringUsesToWriter() throws IOException {
        StringWriter stringWriter = new StringWriter();
        markdownBuffer.appendString("test string");
        markdownBuffer.appendCode("test code");
        markdownBuffer.appendHeader("header");
        assertEquals(markdownBuffer.toString(),markdownBuffer.toWriter(stringWriter).toString());
    }

    @Test
    void requiredLineDoesNotPrintWhenFirst() throws IOException {
        markdownBuffer.appendHeader("test");
        assertStringEquals(new Header("test") + "\n");
    }

    @Test
    void insertsSingleNewlineBetweenStructures() throws IOException {
        markdownBuffer.appendHeader("a");
        markdownBuffer.appendHeader("b");
        assertStringEquals(new Header("a") + "\n" + new Header("b") + "\n");
    }


}