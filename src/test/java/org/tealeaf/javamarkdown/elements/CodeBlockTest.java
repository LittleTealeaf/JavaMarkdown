package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

import static org.junit.jupiter.api.Assertions.*;

class CodeBlockTest {

    @Test
    void asString() {
        String code = "sudo apt install python";
        CodeBlock codeBlock = new CodeBlock(code);
        assertEquals("```\nsudo apt install python\n```", codeBlock.asString());
    }

    @Test
    void asStringLanguage() {
        CodeBlock codeBlock = new CodeBlock("bash", "sudo apt install python");
        assertEquals("```bash\nsudo apt install python\n```", codeBlock.asString());
    }

    @Test
    void requiresNewlineBefore() {
        assertTrue(new CodeBlock("test").requiresNewlineBefore());
    }

    @Test
    void requiresNewlineAfter() {
        assertTrue(new CodeBlock("test").requiresNewlineAfter());
    }

    @Test
    void throwWhenPassedStructure() {
        assertThrows(IllegalContentsException.class, () -> new CodeBlock(new CodeBlock("test")));
    }
}