package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;
import test.Tests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BulletListTest {

    @Test
    void testNameLessBulletList() {

        Object[] obj = {Tests.randomWord(), Tests.randomWord(), Tests.randomWord()};
        BulletList bulletList = new BulletList(obj);
        assertEquals(bulletList.toString(), bulletList.asString());
        assertEquals('-', bulletList.asString().charAt(0));
        assertEquals(obj[2].toString().charAt(obj[2].toString().length() - 1), bulletList.asString().charAt(bulletList.asString().length() - 1));
    }

    @Test
    void testNamedBulletList() {
        Object[] obj = {Tests.randomWord(), Tests.randomWord(), Tests.randomWord()};
        String name = Tests.randomWord();
        BulletList NamedBulletList = new BulletList(name, obj);
        assertEquals(NamedBulletList + "", NamedBulletList.asString());
        assertEquals(obj[2].toString().charAt(obj[2].toString().length() - 1), NamedBulletList.asString().charAt(NamedBulletList.asString().length() - 1));
        assertNotEquals('-', NamedBulletList.asString().charAt(0));
    }

    @Test
    void testNamedEmptyBulletList() {
        String name = Tests.randomWord();
        BulletList NamedEmptyBulletList = new BulletList(name);
        assertEquals(name + "\n", NamedEmptyBulletList.asString());
        assertTrue(!name.isEmpty() && !NamedEmptyBulletList.asString().isEmpty());
    }

    @Test
    void testEmptyBulletList() {
        BulletList EmptyBulletList = new BulletList();
        assertEquals("", EmptyBulletList.asString());
        assertTrue(EmptyBulletList.asString().isBlank() && EmptyBulletList.asString().isBlank());
    }

    @Test
    void testNamedBulletListNotRequiringLineBefore() {
        BulletList bulletList = new BulletList("name");
        assertFalse(bulletList.requiresNewlineBefore());
    }

    @Test
    void testBulletListRequiringLineBefore() {
        assertTrue(new BulletList().requiresNewlineBefore());
    }

    @Test
    void testBulletListRequiringLineAfter() {
        assertTrue(new BulletList().requiresNewlineAfter());
    }

    @Test
    void getPrefix() {
        assertEquals("- ", new BulletList().getPrefix(0));
    }

    @Test
    void testStreamBulletList() {
        List<Object> objectList = List.of("a", "B", "c");
        BulletList bulletList = new BulletList(objectList.stream());
        assertEquals(new BulletList(objectList).asString(), bulletList.asString());
    }

    @Test
    void testListBulletList() {
        List<Object> objects = List.of("a", "b", "C");
        BulletList bulletList = new BulletList(objects);
        assertEquals(new BulletList(objects.toArray()).asString(), bulletList.asString());
    }

    @Test
    void testNameStreamBulletList() {
        List<Object> objects = List.of("a", "b", "C");
        String name = Tests.randomWord();
        assertEquals(new BulletList(name, objects).asString(), new BulletList(name, objects.stream()).asString());
    }

    @Test
    void testNameListBulletList() {
        List<Object> objects = List.of("a", "b", "C");
        String name = Tests.randomWord();
        assertEquals(new BulletList(name, objects.toArray()).asString(), new BulletList(name, objects).asString());
    }
}
