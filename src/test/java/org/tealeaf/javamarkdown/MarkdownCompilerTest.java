package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tealeaf.javamarkdown.elements.*;
import test.Tests;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MarkdownCompilerTest {

    TestCompiler testCompiler;

    @BeforeEach
    void setup() {
        testCompiler = new TestCompiler();
    }



    @Test
    void append() throws IOException {
//        Appends Normal Object
        String word = Tests.randomWord();
        assertSame(testCompiler,testCompiler.append(word));
        assertEquals(Method.STRING, testCompiler.method);
        assertEquals(word, testCompiler.string);

//        Appends Markdown Element
        Bold bold = new Bold(Tests.randomWord());
        assertSame(testCompiler,testCompiler.append(bold));
        assertEquals(Method.MARKDOWN, testCompiler.method);
        assertEquals(bold.toString(), testCompiler.string);
    }

    @Test
    void appendBold() throws IOException {
        String word = Tests.randomWord();
        Bold bold = new Bold(word);
        assertSame(testCompiler,testCompiler.appendBold(word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(bold.toString(),testCompiler.string);
    }

    @Test
    void appendCode() throws IOException {
        String word = Tests.randomWord();
        Code code = new Code(word);
        assertSame(testCompiler,testCompiler.appendCode(word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(code.toString(),testCompiler.string);
    }

    @Test
    void appendStrikethrough() throws IOException {
        String word = Tests.randomWord();
        Strikethrough strikethrough = new Strikethrough(word);
        assertSame(testCompiler,testCompiler.appendStrikethrough(word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(strikethrough.toString(),testCompiler.string);
    }

    @Test
    void appendItalic() throws IOException {
        String word = Tests.randomWord();
        Italic italic = new Italic(word);
        assertSame(testCompiler,testCompiler.appendItalic(word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(italic.toString(),testCompiler.string);
    }

    @Test
    void appendBulletList() throws IOException {
        Object[] objects = new Object[] {"test","test2"};
        BulletList bulletList = new BulletList(objects);
        assertSame(testCompiler,testCompiler.appendBulletList(objects));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(bulletList.toString(),testCompiler.string);
    }

    @Test
    void testAppendBulletList() throws IOException {
        String name = "testname";
        Object[] objects = new Object[] {"test","test2"};
        assertSame(testCompiler,testCompiler.appendBulletList(name,objects));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new BulletList(name,objects).toString(),testCompiler.string);
    }

    @Test
    void appendCodeBlock() throws IOException {
        String code = "cmd + alt + x";
        assertSame(testCompiler,testCompiler.appendCodeBlock(code));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new CodeBlock(code).toString(),testCompiler.string);
    }

    @Test
    void testAppendCodeBlock() throws IOException {
        String language = "python";
        String code = "print(f'testing')";
        assertSame(testCompiler,testCompiler.appendCodeBlock(language,code));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new CodeBlock(language,code).toString(),testCompiler.string);
    }

    @Test
    void appendHeader() throws IOException {
        String word = Tests.randomWord();
        Header header = new Header(word);
        assertSame(testCompiler,testCompiler.appendHeader(word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(header.toString(),testCompiler.string);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    void appendHeaderLevel(int level) throws IOException {
        String word = Tests.randomWord();
        Header header = new Header(level,word);
        assertSame(testCompiler,testCompiler.appendHeader(level,word));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(header.toString(),testCompiler.string);
    }

    @Test
    void appendImage() throws IOException {
        String url = "https://randomurl.com/image.png";
        assertSame(testCompiler,testCompiler.appendImage(url));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new Image(url).toString(),testCompiler.string);
    }

    @Test
    void testAppendImage() throws IOException {
        String alt = "test";
        String url = "https://randomurl.com/image.png";
        assertSame(testCompiler,testCompiler.appendImage(alt,url));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new Image(alt,url).toString(),testCompiler.string);
    }

    @Test
    void appendLink() throws IOException {
        String url = "url";
        assertSame(testCompiler,testCompiler.appendLink(url));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new Link(url).toString(),testCompiler.string);
    }

    @Test
    void testAppendLink() throws IOException {
        String text = "display";
        String url = "url";
        assertSame(testCompiler,testCompiler.appendLink(text,url));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new Link(text,url).toString(),testCompiler.string);
    }

    @Test
    void appendNumberedList() throws IOException {
        Object[] objects = new Object[] {"test","test2"};
        assertSame(testCompiler,testCompiler.appendNumberedList(objects));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new NumberedList(objects).toString(),testCompiler.string);
    }

    @Test
    void testAppendNumberedList() throws IOException {
        String name = "list name";
        Object[] objects = new Object[] {"test","test2"};
        assertSame(testCompiler,testCompiler.appendNumberedList(name,objects));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(new NumberedList(name,objects).toString(),testCompiler.string);
    }

    static class TestCompiler implements MarkdownCompiler<TestCompiler> {

        String string;
        Method method;

        @Override
        public TestCompiler appendString(String string) throws IOException {
            this.string = string;
            method = Method.STRING;
            return this;
        }

        @Override
        public TestCompiler appendMarkdownElement(MarkdownElement element) throws IOException {
            this.string = element.toString();
            method = Method.MARKDOWN;
            return this;
        }
    }

    enum Method {
        MARKDOWN,
        STRING;
    }
}