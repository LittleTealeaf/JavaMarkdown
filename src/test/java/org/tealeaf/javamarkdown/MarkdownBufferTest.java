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
        assertEquals(new Header("test") + "\n",markdownBuffer.toString());
    }


}