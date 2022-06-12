package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.tealeaf.javamarkdown.exceptions.IllegalHeaderLevelException;
import test.Tests;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class HeaderTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void legalHeaderLevel(int level) {
        assertDoesNotThrow(() -> new Header(level,Tests.randomSentence()));
    }

    @Test
    void illegalHeaderLevel() {
        assertThrows(IllegalHeaderLevelException.class,() -> new Header(Tests.randomInteger(-10000,1),Tests.randomSentence()));
        assertThrows(IllegalHeaderLevelException.class,() -> new Header(Tests.randomInteger(7,10000),Tests.randomSentence()));
    }

    @Test
    void toWriter() throws IOException {
        String contents = Tests.randomSentence();
        Header header = new Header(contents);
        assertEquals("# " + contents,header.toWriter(new StringWriter()).toString());
    }

    @Test
    void asString() {
        String contents = Tests.randomSentence();
        assertEquals("# " + contents,new Header(contents).asString());
    }

    @Test
    void requiresNewlineBefore() {
        assertTrue(new Header(Tests.randomWord()).requiresNewlineBefore());
    }

    @Test
    void requiresNewlineAfter() {
        assertTrue(new Header(Tests.randomWord()).requiresNewlineAfter());
    }
}