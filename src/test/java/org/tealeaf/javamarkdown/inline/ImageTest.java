package org.tealeaf.javamarkdown.inline;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.IllegalContentsException;
import static org.junit.jupiter.api.Assertions.*;


public class ImageTest {

    @Test
    void testAsString() throws IllegalContentsException {
        String src = "https://avatars.githubusercontent.com/u/35083315?v=4";
        Image image = new Image(src);

        assertFalse(src.isEmpty());
        assertEquals("![]("+src+")", image.asString());

    }

    }
