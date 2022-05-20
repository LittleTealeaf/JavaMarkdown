package org.tealeaf.javamarkdown.markup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Structure;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static test.Tests.randomWord;

class BoldTest {

    static Stream<Arguments> provideIllegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(true, Bold.class, Structure.class));
    }

    static Stream<Arguments> provideLegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(false, Bold.class, Structure.class));
    }

    @Test
    void testToWriter() throws IllegalContentsException, IOException {
        String word = randomWord();
        Bold bold = new Bold(word);
        StringWriter writer = new StringWriter();
        assertEquals("**" + word + "**", bold.toWriter(writer).toString());
    }

    @Test
    void testAsString() throws IllegalContentsException {
        String word = randomWord();
        Bold bold = new Bold(word);
        assertEquals("**" + word + "**", bold.asString());
    }

    @Test
    void testToString() throws IllegalContentsException {
        Bold bold = new Bold(randomWord());
        assertEquals(bold.asString(), bold.toString());
    }

    @ParameterizedTest
    @MethodSource("provideIllegalObjects")
    void testIllegalObject(Object object) {
        assertThrows(IllegalContentsException.class, () -> new Bold(object));
    }

    @ParameterizedTest
    @MethodSource("provideLegalObjects")
    void testLegalObject(Object object) {
        assertDoesNotThrow(() -> new Bold(object));
    }
}