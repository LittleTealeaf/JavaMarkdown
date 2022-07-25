package org.tealeaf.javamarkdown;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.Bold;
import org.tealeaf.javamarkdown.elements.CodeBlock;
import org.tealeaf.javamarkdown.elements.Italic;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownElementTest {

    @Test
    void asStringNewlineBefore() {
        CodeBlock codeBlock = new CodeBlock("some code");
        assertTrue(codeBlock.requiresNewlineBefore());
        assertEquals('\n', codeBlock.asString(false).charAt(0));
        assertNotEquals('\n', codeBlock.asString(true).charAt(0));

        Italic italic = new Italic("testing");
        assertFalse(italic.requiresNewlineBefore());
        assertNotEquals('\n', italic.asString(false).charAt(0));
        assertNotEquals('\n', italic.asString(true).charAt(0));

        MarkdownElement custom = new Bold("test") {
            @Override
            public boolean requiresNewlineAfter() {
                return false;
            }

            @Override
            public boolean requiresNewlineBefore() {
                return true;
            }
        };
        assertEquals('\n',custom.asString(false).charAt(0));
    }

    @Test
    void asStringNewLineAfter() {
        CodeBlock codeBlock = new CodeBlock("testing");
        assertTrue(codeBlock.requiresNewlineAfter());
        String codeStringFalse = codeBlock.asString(false);
        assertEquals('\n', codeStringFalse.charAt(codeStringFalse.length() - 1));
        String codeStringTrue = codeBlock.asString(true);
        assertEquals('\n', codeStringTrue.charAt(codeStringTrue.length() - 1));

        Italic italic = new Italic("testing");
        assertFalse(italic.requiresNewlineAfter());
        String italicTrue = italic.asString(true);
        assertNotEquals('\n', italicTrue.charAt(italicTrue.length() - 1));
        String italicFalse = italic.asString(false);
        assertNotEquals('\n', italicFalse.charAt(italicFalse.length() - 1));
    }
}