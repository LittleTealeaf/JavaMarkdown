package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tealeaf.javamarkdown.elements.*;
import org.tealeaf.javamarkdown.exceptions.IllegalHeaderLevelException;
import test.Tests;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownCompilerTest {

    public TestCompiler testCompiler;
    public String sentence;
    public String word;

    public String url;

    public Object[] array;
    public List<Object> list;
    public List<Object> objectStream;

    public Stream<Object> stream() {
        return objectStream.stream();
    }

    int number;

    @BeforeEach
    void setup() {
        testCompiler = new TestCompiler();
        sentence = Tests.randomSentence();
        word = Tests.randomWord();
        url = Tests.randomURL();
        array = Tests.randomWordsStream(Tests.randomInteger(0,50)).toList().toArray();
        list = Tests.randomWordsStream(Tests.randomInteger(0,50)).collect(Collectors.toList());
        objectStream = Tests.randomWordsStream(Tests.randomInteger(0,50)).collect(Collectors.toList());
        number = Tests.randomInteger(1,100);
    }

    void testMethod(CompilerExecutable compilerExecutable, MarkdownElement markdownElement) throws IOException {
        testMethod(compilerExecutable,markdownElement.asString());
    }

    void testMethod(CompilerExecutable compilerExecutable, String expectedValue) throws IOException {
        assertEquals(testCompiler, compilerExecutable.execute(testCompiler));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(expectedValue,testCompiler.string);
    }

    @Test
    void appendBold() throws IOException {
        testMethod(e -> e.appendBold("test"), "**test**");
    }

    @Test
    void appendCode() throws IOException {
        testMethod(e -> e.appendCode("test"), "`test`");
    }

    @Test
    void appendBulletListObjects() throws IOException {
        Object[] objects = {"me","mi","mo"};
        testMethod(e -> e.appendBulletList(objects),new BulletList(objects).toString());
    }

    @Test
    void appendBulletListNameObjects() throws IOException {
        String name = "testing name";
        Object[] objects = {"me","mawe","fwe"};
        testMethod(e -> e.appendBulletList(name,objects),new BulletList(name,objects).toString());
    }

    @Test
    void appendCodeBlock() throws IOException {
        String content = Tests.randomSentence();
        testMethod(e -> e.appendCodeBlock(content),new CodeBlock(content).toString());
    }

    @Test
    void appendCodeBlockLanguage() throws IOException {
        String content = Tests.randomSentence();
        String language = Tests.randomWord();
        testMethod(e -> e.appendCodeBlock(language,content),new CodeBlock(language,content).toString());
    }

    @Test
    void appendHeader() throws IOException {
        String content = Tests.randomSentence();
        testMethod(e -> e.appendHeader(content),new Header(content).toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    void appendHeaderValidLevels(int level) throws IOException {
        String content = Tests.randomSentence();
        testMethod(e -> e.appendHeader(level,content),new Header(level,content).toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,7})
    void appendHeaderInvalidLevels(int level) {
        assertThrows(IllegalHeaderLevelException.class,() -> testCompiler.appendHeader(level,Tests.randomSentence()));
    }

    @Test
    void appendImage() throws IOException {
        String url = Tests.randomURL();
        testMethod(e -> e.appendImage(url),new Image(url).toString());
    }

    @Test
    void appendImageContent() throws IOException {
        String url = Tests.randomURL();
        String content = Tests.randomSentence();
        testMethod(e -> e.appendImage(content,url),new Image(content,url).toString());
    }

    @Test
    void appendStrikethrough() throws IOException {
        testMethod(e -> e.appendStrikethrough(sentence),new Strikethrough(sentence).toString());
    }

    @Test
    void appendItalic() throws IOException {
        testMethod(e -> e.appendItalic(sentence), new Italic(sentence).toString());
    }

    @Test
    void appendLink() throws IOException {
        testMethod(e -> e.appendLink(url),new Link(url).toString());
    }

    @Test
    void appendLinkContent() throws IOException {
        testMethod(e -> e.appendLink(sentence,url),new Link(sentence,url).toString());
    }

    @Test
    void appendNumberedListArray() throws IOException {
        Object[] objects = {"a","b","c"};
        testMethod(e -> e.appendNumberedList(objects),new NumberedList(objects).toString());
    }

    @Test
    void appendNumberedListNameArray() throws IOException {
        Object[] objects = {"a","b","c"};
        testMethod(e -> e.appendNumberedList(word,objects),new NumberedList(word,objects).toString());
    }

    @Test
    void appendNumberedListStartArray() throws IOException {
        Object[] objects = {"a","b","c","d"};
        int start = Tests.randomInteger();
        testMethod(e -> e.appendNumberedList(start,objects),new NumberedList(start,objects));
    }

    @Test
    void appendNumberedListNameStartArray() throws IOException {
        testMethod(e -> e.appendNumberedList(word,number,array),new NumberedList(word,number,array));
    }

    @Test
    void appendNumberedListList() throws IOException {
        testMethod(e -> e.appendNumberedList(list),new NumberedList(list));
    }

    @Test
    void appendNumberedListStream() throws IOException {
        testMethod(e -> e.appendNumberedList(stream()), new NumberedList(stream()));
    }

    @Test
    void appendNumberedListStartList() throws IOException {
        testMethod(e -> e.appendNumberedList(number,list), new NumberedList(number,list));
    }

    @Test
    void appendNumberedListStartStream() throws IOException {
        testMethod(e -> e.appendNumberedList(number,stream()), new NumberedList(number,stream()));
    }

    @Test
    void appendNumberedListNameList() throws IOException {
        testMethod(e -> e.appendNumberedList(word,list), new NumberedList(word,list));
    }

    @Test
    void appendNumberedListNameStream() throws IOException {
        testMethod(e -> e.appendNumberedList(word,stream()), new NumberedList(word,stream()));
    }

    @Test
    void appendNumberedListNameStartList() throws IOException {
        testMethod(e -> e.appendNumberedList(word,number,list),new NumberedList(word,number,list));
    }

    @Test
    void appendNumberedListNameStartStream() throws IOException {
        testMethod(e -> e.appendNumberedList(word,number,stream()), new NumberedList(word,number,stream()));
    }

    @Test
    void appendWithMarkdownElement() throws IOException {
        Bold bold = new Bold("test");
        assertSame(testCompiler,testCompiler.append(bold));
        assertEquals(Method.MARKDOWN,testCompiler.method);
        assertEquals(bold.asString(),testCompiler.string);
    }

    @Test
    void appendWithString() throws IOException {
        assertSame(testCompiler,testCompiler.append(word));
        assertEquals(Method.STRING,testCompiler.method);
        assertEquals(word,testCompiler.string);
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

    interface CompilerExecutable {
        TestCompiler execute(TestCompiler testCompiler) throws IOException;
    }
}