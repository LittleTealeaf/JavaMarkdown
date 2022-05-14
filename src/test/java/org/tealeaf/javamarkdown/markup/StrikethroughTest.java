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

class StrikethroughTest {

    @Test
    void toWriter() throws IllegalContentsException, IOException {
        String word = Tests.randomWord();
        Strikethrough strikethrough = new Strikethrough(word);
        StringWriter writer = new StringWriter();
        assertEquals("~~" + word + "~~",strikethrough.toWriter(writer).toString());
    }

    @Test
    void asString() throws IllegalContentsException {
        String word = Tests.randomWord();
        Strikethrough strikethrough = new Strikethrough(word);
        assertEquals("~~" + word + "~~",strikethrough.asString());
    }

    @Test
    void testToString() throws IllegalContentsException {
        Strikethrough strikethrough = new Strikethrough(Tests.randomWord());
        assertEquals(strikethrough.asString(),strikethrough.toString());
    }
    static Stream<Arguments> provideIllegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(true, Strikethrough.class, Structure.class));
    }

    static Stream<Arguments> provideLegalObjects() throws IllegalContentsException {
        return Tests.provideArguments(Tests.filterClasses(false,Strikethrough.class,Structure.class));
    }

    @ParameterizedTest
    @MethodSource("provideIllegalObjects")
    void testIllegalObject(Object object) {
        assertThrows(IllegalContentsException.class,() -> new Strikethrough(object));
    }

    @ParameterizedTest
    @MethodSource("provideLegalObjects")
    void testLegalObject(Object object) {
        assertDoesNotThrow(() -> new Strikethrough(object));
    }

}