package org.tealeaf.javamarkdown;

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

    static Stream<Arguments> provideAppendObjects() throws IllegalContentsException {
        return Tests.provideArguments();
    }

    @Test
    void testConstructorEmpty() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
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
        MarkdownWriter builder = new MarkdownWriter(writer);
        assertEquals(writer.toString(), builder.getWriter().toString());
        builder.close();
    }

    @ParameterizedTest
    @MethodSource("provideAppendObjects")
    void append(Object item) throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            builder.append(item);
            assertTrue(builder.getWriter().toString().length() > 0);
        }
    }

    @Test
    void append() throws IOException {
        String input = Tests.randomSentence();
        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals(input, builder.appendString(input).toString());
        }
    }

    @Test
    void appendBold() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String sentence = Tests.randomSentence();
            assertEquals("**" + sentence + "**", builder.appendBold(sentence).getWriter().toString());
        }
    }

    @Test
    void appendBoldReturnsBuilder() throws IOException {
        MarkdownWriter builder = new MarkdownWriter();
        assertSame(builder, builder.appendBold(Tests.randomSentence()));
    }

    @Test
    void appendItalic() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String sentence = Tests.randomSentence();
            assertEquals("*" + sentence + "*", builder.appendItalic(sentence).getWriter().toString());
        }
    }

    @Test
    void appendItalicReturnsBuilder() throws IOException {
        MarkdownWriter markdownWriter = new MarkdownWriter();
        assertSame(markdownWriter, markdownWriter.appendItalic(Tests.randomSentence()));
    }

    @Test
    void appendStrikethrough() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String sentence = Tests.randomSentence();
            assertEquals("~~" + sentence + "~~", builder.appendStrikethrough(sentence).getWriter().toString());
        }
    }

    @Test
    void appendStrikethroughReturnsBuilder() throws IOException {
        MarkdownWriter builder = new MarkdownWriter();
        assertSame(builder, builder.appendStrikethrough(Tests.randomSentence()));
    }

    @Test
    void appendCode() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String sentence = Tests.randomSentence();
            assertEquals("`" + sentence + "`", builder.appendCode(sentence).getWriter().toString());
        }
    }

    @Test
    void appendCodeReturnsWriter() throws IOException {
        MarkdownWriter markdownWriter = new MarkdownWriter();
        assertSame(markdownWriter, markdownWriter.appendCode(Tests.randomSentence()));
    }

    @Test
    void appendLink() throws IOException {
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String content = Tests.randomSentence(), url = Tests.randomURL();
            assertEquals("[" + content + "](" + url + ")", builder.appendLink(content, url).toString());
        }
    }

    @Test
    void appendLinkReturnsBuilder() throws IOException {
        MarkdownWriter builder = new MarkdownWriter();
        assertSame(builder, builder.appendLink(Tests.randomSentence(), Tests.randomURL()));
    }

    @Test
    void getWriter() throws IOException {
        Writer writer = new StringWriter();
        MarkdownWriter builder = new MarkdownWriter(writer);
        assertSame(writer, builder.getWriter());

        try (MarkdownWriter builder1 = new MarkdownWriter()) {
            assertNotNull(builder1.getWriter());
        }

        builder.close();
    }

    @Test
    void testToString() throws IOException {
        try (MarkdownWriter markdownWriter = new MarkdownWriter()) {
            String sentence = Tests.randomSentence();
            markdownWriter.append(sentence);
            assertEquals(sentence, markdownWriter.toString());
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
        try (MarkdownWriter builder = new MarkdownWriter()) {
            String url = Tests.randomURL("png");
            String desc = "A boy";
            assertEquals("!["+desc+"](" + url + ")", builder.appendImage(desc,url).toString());
        }
    }

    @Test
    void appendImageReturnsBuilder() throws IOException {
        MarkdownWriter builder = new MarkdownWriter();
        String desc = "A boy";
        assertSame(builder, builder.appendImage(desc,Tests.randomURL("png")));
    }

    @Test
    void appendMarkdownItem() throws IOException {
//        Using bold as an example
        Bold bold = new Bold(Tests.randomWord());
        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals(bold.asString(), builder.appendMarkdownElement(bold).toString());
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

        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals("\n" + bold.asString(), builder.appendMarkdownElement(bold).toString());
        }

        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals("\n" + bold.asString(), builder.append((Object) "\n").appendMarkdownElement(bold).toString());
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

        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals(bold.asString() + "\n", builder.appendMarkdownElement(bold).toString());
        }
    }

    @Test
    void appendBulletList() throws IOException {
        Object[] items = Tests.randomWords();
        BulletList bulletList = new BulletList(items);

        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertEquals("\n" + bulletList.asString() + "\n", builder.appendBulletList(items).toString());
        }
    }

    @Test
    void appendBulletListReturnsBuilder() throws IOException {
        MarkdownWriter builder = new MarkdownWriter();
        assertSame(builder,builder.appendBulletList((Object[]) Tests.randomWords()));
    }

    @Test
    void appendNumberedList() throws IOException {
        Object[] items = Tests.randomWords();
        NumberedList numberedList = new NumberedList(items);

        try (MarkdownWriter builder = new MarkdownWriter()) {
            assertSame(builder,builder.appendNumberedList(items));
            assertEquals("\n" + numberedList.asString() + "\n", builder.toString());
        }
    }

    @Test
    void appendHeader() throws IOException {
        String content = Tests.randomSentence();
        Header header = new Header(content);

        try (MarkdownWriter writer = new MarkdownWriter()) {
            assertSame(writer,writer.appendHeader(content));
            assertEquals("\n" + header.asString() + "\n", writer.toString());
        }
    }


    @Test
    void appendHeaderLevel() throws IOException {
        String content = Tests.randomSentence();
        int level = Tests.randomInteger(1,7);
        Header header = new Header(level,content);

        try (MarkdownWriter writer = new MarkdownWriter()) {
            assertSame(writer,writer.appendHeader(level,content));
            assertEquals("\n" + header.asString() + "\n", writer.toString());
        }
    }

    @Test
    void appendCodeBlock() throws  IOException {
        Object content = "double i = Math.random()";
        CodeBlock code = new CodeBlock(content);

        try(MarkdownWriter writer = new MarkdownWriter()){
            assertSame(writer,writer.appendCodeBlock(content));
            assertEquals("\n" + code.asString() + "\n",writer.toString());
        }
    }

    @Test
    void appendCodeBlockWithLanguages() throws IOException{
        Object content = "double i = Math.random()";
        String language = "java";
        CodeBlock code = new CodeBlock(language,content);
        try(MarkdownWriter writer = new MarkdownWriter()){
            assertSame(writer,writer.appendCodeBlock(language,content));
            assertEquals("\n" + code.asString() + "\n", writer.toString());
        }
    }



}
