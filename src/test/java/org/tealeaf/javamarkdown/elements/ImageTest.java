package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

import static org.junit.jupiter.api.Assertions.*;


public class ImageTest {

    @Test
    void testAsString() throws IllegalContentsException {
        String src = "https://avatars.githubusercontent.com/u/35083315?v=4";
        String desc = "A boy";
        Image image = new Image(desc, src);

        assertFalse(src.isEmpty());
        assertEquals("!["+desc+"]("+src+")", image.asString());
        assertFalse(desc.length() < 0);
        assertEquals(image.asString().charAt(0), '!');
        assertEquals(image.asString().charAt(image.asString().length() - 1), ')');

    }

    @Test
    void testAsStringNoContent() throws IllegalContentsException {
        String src = "https://avatars.githubusercontent.com/u/35083315?v=4";
        Image image = new Image(src);

        assertEquals("![](" + src + ")",image.asString());
    }

    }
