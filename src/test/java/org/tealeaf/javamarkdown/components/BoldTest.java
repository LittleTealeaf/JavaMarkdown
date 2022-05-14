package org.tealeaf.javamarkdown.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoldTest extends SimpleMarkupTest{

    @Test
    void testBold() {
        testMarkup("**");
    }
    @Override
    public SimpleMarkup generate(Object object) {
        return new Bold(object);
    }
}