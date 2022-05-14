package org.tealeaf.javamarkdown.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItalicsTest extends SimpleMarkupTest {


    @Test
    void testItalics() {
        testMarkup("*");
    }

    @Override
    public SimpleMarkup generate(Object object) {
        return new Italics(object);
    }
}