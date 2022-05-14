package org.tealeaf.javamarkdown.markup;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.IllegalContentsException;
import test.Randoms;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ItalicsTest {

    @Test
    void testAsString() throws IllegalContentsException {
        String word = Randoms.randomWord();
        assertEquals("*" + word + "*",new Italics(word).asString());
    }

    @Test
    void testToString() throws IllegalContentsException {
        String word = Randoms.randomWord();
        assertEquals("*" + word + "*",new Italics(word).toString());
    }

    @Test
    void testToWriter() throws IllegalContentsException, IOException {
        String word = Randoms.randomWord();
        StringWriter stringWriter = (StringWriter) new Italics(word).toWriter(new StringWriter());
        stringWriter.close();
        assertEquals("*" + word + "*",stringWriter.toString());
    }

}