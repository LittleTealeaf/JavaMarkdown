package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tealeaf.javamarkdown.markup.Bold;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownBuilderTest {

    static Stream<Arguments> provideAppendObjects() throws IllegalContentsException {
        return Tests.provideArguments();
    }

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
            assertTrue(builder.getWriter().toString().length() > 0);
        }
    }

    @Test
    void append() throws IOException {
        String input = Tests.randomSentence();
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertEquals(input, builder.appendString(input).toString());
        }
    }

    @Test
    void appendBold() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            assertEquals("**" + sentence + "**", builder.appendBold(sentence).getWriter().toString());
        }
    }

    @Test
    void appendBoldReturnsBuilder() throws IOException {
        MarkdownBuilder builder = new MarkdownBuilder();
        assertSame(builder, builder.appendBold(Tests.randomSentence()));
    }

    @Test
    void appendItalic() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            assertEquals("*" + sentence + "*", builder.appendItalic(sentence).getWriter().toString());
        }
    }

    @Test
    void appendItalicReturnsBuilder() throws IOException {
        MarkdownBuilder markdownBuilder = new MarkdownBuilder();
        assertSame(markdownBuilder, markdownBuilder.appendItalic(Tests.randomSentence()));
    }

    @Test
    void appendStrikethrough() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            assertEquals("~~" + sentence + "~~", builder.appendStrikethrough(sentence).getWriter().toString());
        }
    }

    @Test
    void appendStrikethroughReturnsBuilder() throws IOException {
        MarkdownBuilder builder = new MarkdownBuilder();
        assertSame(builder, builder.appendStrikethrough(Tests.randomSentence()));
    }

    @Test
    void appendCode() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String sentence = Tests.randomSentence();
            assertEquals("`" + sentence + "`", builder.appendCode(sentence).getWriter().toString());
        }
    }

    @Test
    void appendCodeReturnsWriter() throws IOException {
        MarkdownBuilder markdownBuilder = new MarkdownBuilder();
        assertSame(markdownBuilder, markdownBuilder.appendCode(Tests.randomSentence()));
    }

    @Test
    void appendLink() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String content = Tests.randomSentence(), url = Tests.randomURL();
            assertEquals("[" + content + "](" + url + ")", builder.appendLink(content, url).toString());
        }
    }

    @Test
    void appendLinkReturnsBuilder() throws IOException {
        MarkdownBuilder builder = new MarkdownBuilder();
        assertSame(builder, builder.appendLink(Tests.randomSentence(), Tests.randomURL()));
    }

    @Test
    void getWriter() throws IOException {
        Writer writer = new StringWriter();
        MarkdownBuilder builder = new MarkdownBuilder(writer);
        assertSame(writer, builder.getWriter());

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

    @Test
    void write() {
    }

    @Test
    void flush() {
    }

    @Test
    void close() {
    }

    @Test
    void appendImage() throws IOException {
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            String url = Tests.randomURL("png");
            assertEquals("![](" + url + ")", builder.appendImage(url).toString());
        }
    }

    @Test
    void appendImageReturnsBuilder() throws IOException {
        MarkdownBuilder builder = new MarkdownBuilder();
        assertSame(builder, builder.appendImage(Tests.randomURL("png")));
    }

    @Test
    void appendMarkdownItem() throws IOException {
//        Using bold as an example
        Bold bold = new Bold(Tests.randomWord());
        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertEquals(bold.asString(), builder.appendMarkdownItem(bold).toString());
        }
    }

    @Test
    void appendMarkdownItemNewlineBefore() throws IOException {
        Bold bold = new Bold(Tests.randomWord()) {
            @Override
            public boolean requiresNewlineBefore() {
                return true;
            }
        };

        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertEquals("\n" + bold.asString(), builder.appendMarkdownItem(bold).toString());
        }

        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertEquals("\n" + bold.asString(), builder.append((Object) "\n").appendMarkdownItem(bold).toString());
        }
    }

    @Test
    void appendMarkdownItemNewLineAfter() throws IOException {
        Bold bold = new Bold(Tests.randomWord()) {
            @Override
            public boolean requiresNewlineAfter() {
                return true;
            }
        };

        try (MarkdownBuilder builder = new MarkdownBuilder()) {
            assertEquals(bold.asString() + "\n", builder.appendMarkdownItem(bold).toString());
        }
    }

    @Test
    void testToString1() {
    }
}