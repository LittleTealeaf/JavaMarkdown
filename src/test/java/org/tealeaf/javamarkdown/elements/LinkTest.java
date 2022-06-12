package org.tealeaf.javamarkdown.elements;
import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

import java.io.IOException;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {
    static String url = "https://www.goodreads.com/";
    static String content = "Books";
    @Test
    void testToWriter() throws IllegalContentsException, IOException{

       Link link = new Link(content, url);
       StringWriter writer = new StringWriter();
       assertEquals( "["+content+"]" + "("+url+")" ,link.toWriter(writer).toString());
    }

    @Test
    void testAsString(){
        Link link = new Link(content,url);
        assertTrue(link.asString().contains(content));
        assertTrue(link.asString().contains(url));
        assertEquals("["+content+"]" + "("+url+")", link.asString());
        assertEquals('[', link.asString().charAt(0));
        assertEquals(')', link.asString().charAt(link.asString().length() - 1));
    }

    @Test
    void testAsStringNoContent() {
        Link link = new Link(url);
        assertTrue(link.asString().contains(url));
        assertEquals("[" + url + "](" + url + ")",link.asString());
    }

    @Test
    void requiresNewlineBefore() {
        assertFalse(new Link("url").requiresNewlineBefore());
    }

    @Test
    void requiresNewlineAfter() {
        assertFalse(new Link("url").requiresNewlineAfter());
    }
}
