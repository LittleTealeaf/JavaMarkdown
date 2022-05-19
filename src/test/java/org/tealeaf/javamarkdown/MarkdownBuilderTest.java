package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownBuilderTest {

    @Test
    void testConstructorEmpty() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertNotNull(builder.getWriter());
            String sentence = Tests.randomSentence();
            builder.append(sentence);
            assertEquals(sentence, builder.getWriter().toString());
        }
    }

    @Test
    void testConstructorNotEmpty() throws IOException {
        Writer writer = new StringWriter();
        writer.append(Tests.randomWord());
        MarkdownBuilder builder = new MarkdownBuilder(writer);
        assertEquals(writer.toString(), builder.getWriter().toString());
    }

    @ParameterizedTest
    @MethodSource("provideAppendObjects")
    void append(Object item) throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            builder.append(item);
            assertEquals(item.toString(), builder.getWriter().toString());
        }
    }

    @Test
    void appendBold() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            builder.appendBold(sentence);
            assertEquals("**" + sentence + "**", builder.getWriter().toString());
        }
    }

    @Test
    void appendItalic() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            builder.appendItalic(sentence);
            assertEquals("*" + sentence + "*", builder.getWriter().toString());
        }
    }

    @Test
    void appendStrikethrough() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            builder.appendStrikethrough(sentence);
            assertEquals("~~" + sentence + "~~", builder.getWriter().toString());
        }
    }

    @Test
    void appendCode() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            builder.appendCode(sentence);
            assertEquals("`" + sentence + "`", builder.getWriter().toString());
        }
    }

    @Test
    void getWriter() throws IOException {
        Writer writer = new StringWriter();
        MarkdownBuilder builder = new MarkdownBuilder(writer);
        assertSame(writer,builder.getWriter());

        try (MarkdownBuilder builder1 = new MarkdownBuilder()) {
            assertNotNull(builder1.getWriter());
        }
    }

    @Test
    void testToString() throws IOException {
        try (MarkdownBuilder markdownBuilder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            markdownBuilder.append(sentence);
            assertEquals(sentence, markdownBuilder.toString());
        }
    }

    static Stream<Arguments> provideAppendObjects() throws IllegalContentsException {
        return Tests.provideArguments();
    }
}