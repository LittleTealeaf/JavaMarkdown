package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.*;
import test.Tests;

import static org.junit.jupiter.api.Assertions.*;

public class NumberedListTest {

    @Test
    void testEmptyNumberedList() {
        NumberedList empty =  new NumberedList();
        assertEquals("",empty.asString());
        assertTrue(empty.asString().isBlank());
    }

    @Test
    void testEmptyNamedNumberedList() {
        String name = Tests.randomWord();
        NumberedList emptyNamed = new NumberedList(name);
        assertEquals(name + "\n", emptyNamed.asString());
        assertTrue(!name.isBlank() && !emptyNamed.asString().isBlank());
        emptyNamed.add(Tests.randomWord(),Tests.randomWord(),Tests.randomWord());
        assertEquals(emptyNamed.toString(), emptyNamed.asString());
    }

    @Test
    void testNameLessIndexedNumberedList() {
        int random = 15;
        NumberedList indexed  = new NumberedList(random);
        assertEquals("", indexed.asString());
        indexed.add(Tests.randomWord(),Tests.randomWord(),Tests.randomWord());
        assertEquals(indexed.toString(), indexed.asString());
        assertEquals(Integer.toString(random),indexed.asString().substring(0,2));
    }

    @Test
    void testNamedIndexedNumberedList(){
        int random = (int) (Math.random() * 20);
        String name = Tests.randomWord();
        NumberedList NamedIndexed  = new NumberedList(name,random);
        assertEquals(NamedIndexed.toString(), NamedIndexed.asString());
        assertEquals(name, NamedIndexed.asString().substring(0, name.length()));

    }

    @Test
    void testNamedObjectNumberedList() {
        String name = Tests.randomWord();
        Object[] list = { Tests.randomWord(), Tests.randomWord(),Tests.randomWord()};
        NumberedList NamedObjectList = new NumberedList(name,list);
        assertEquals(NamedObjectList.toString(), NamedObjectList.asString());
        assertEquals(name, NamedObjectList.asString().substring(0, name.length()));

    }

    @Test
    void testIndexedObjectNumberedList(){
        int random = 20;
        Object[] list = { Tests.randomWord(), Tests.randomWord(),Tests.randomWord()};
        NumberedList IndexedObjectList = new NumberedList(random,list);
        assertEquals(IndexedObjectList.toString(), IndexedObjectList.asString());
        assertEquals(Integer.toString(random), IndexedObjectList.asString().substring(0,2));
    }

    @Test
    void testNamedIndexedObjectNumberedList() {
        int random = 20;
        Object[] list = { Tests.randomWord(), Tests.randomWord(),Tests.randomWord()};
        String name = Tests.randomWord();
        int firstLength = name.length();
        NumberedList allParam = new NumberedList(name,random,list);
        assertEquals(name, allParam.asString().substring(0,name.length()));
        assertEquals(Integer.toString(random),allParam.asString().substring(firstLength+1,firstLength+3));
        assertEquals(list[0].toString(),allParam.asString().substring(firstLength+5,firstLength+5+(list[0].toString().length())));
        assertEquals(allParam.toString(), allParam.asString());
    }

    @Test
    void getPrefix() {
        assertEquals("1. ",new NumberedList().getPrefix(0));
    }

    @Test
    void getPrefixOffset() {
        assertEquals("4. ",new NumberedList(4).getPrefix(0));
    }

}
