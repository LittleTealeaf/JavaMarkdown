package org.tealeaf.javamarkdown.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoldTest {

    @Test
    void appendOn() {
        String item = "test";
        Bold bond = new Bold(item);
        StringBuilder builder = bond.appendOn(new StringBuilder());
        assertEquals("**" + item + "**",builder.toString());
    }
}