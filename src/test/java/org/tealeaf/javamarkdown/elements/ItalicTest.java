package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.elements.Italic;
import org.tealeaf.javamarkdown.types.Structure;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ItalicTest {

    static Stream<Arguments> provideIllegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(true, Italic.class, Structure.class));
    }

    static Stream<Arguments> provideLegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(false, Italic.class, Structure.class));
    }

    @Test
    void toWriter() throws IllegalContentsException, IOException {
        String word = Tests.randomWord();
        Italic italic = new Italic(word);
        StringWriter writer = new StringWriter();
        assertEquals("*" + word + "*", italic.toWriter(writer).toString());
    }

    @Test
    void asString() throws IllegalContentsException {
        String word = Tests.randomWord();
        Italic italic = new Italic(word);
        assertEquals("*" + word + "*", italic.asString());
    }

    @Test
    void testToString() throws IllegalContentsException {
        Italic italic = new Italic(Tests.randomWord());
        assertEquals(italic.asString(), italic.toString());
    }

    @ParameterizedTest
    @MethodSource("provideIllegalObjects")
    void testIllegalObject(Object object) {
        assertThrows(IllegalContentsException.class, () -> new Italic(object));
    }

    @ParameterizedTest
    @MethodSource("provideLegalObjects")
    void testLegalObjects(Object object) {
        assertDoesNotThrow(() -> new Italic(object));
    }
}