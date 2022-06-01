package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    static Stream<Arguments> provideIllegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(true, MarkdownElement.class));
    }

    static Stream<Arguments> provideLegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(false, MarkdownElement.class));
    }

    @Test
    void toWriter() throws IOException {
        String word = Tests.randomWord();
        Code code = new Code(word);
        StringWriter writer = new StringWriter();
        assertEquals("`" + word + "`", code.toWriter(writer).toString());
    }

    @Test
    void asString() {
        String word = Tests.randomWord();
        Code code = new Code(word);
        assertEquals("`" + word + "`", code.asString());
    }

    @Test
    void testToString() {
        Code code = new Code(Tests.randomWord());
        assertEquals(code.asString(), code.toString());
    }

    @ParameterizedTest
    @MethodSource("provideIllegalObjects")
    void testIllegalObject(Object object) {
        assertThrows(IllegalContentsException.class, () -> new Code(object));
    }

    @ParameterizedTest
    @MethodSource("provideLegalObjects")
    void testLegalObjects(Object object) {
        assertDoesNotThrow(() -> new Code(object));
    }
}
