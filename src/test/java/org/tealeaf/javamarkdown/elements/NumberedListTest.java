package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.*;
import test.Tests;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class NumberedListTest {

    void assertItemCount(NumberedList numberedList, int count) {
        assertEquals(count,numberedList.getItems().size());
    }

    void assertNullName(NumberedList numberedList) {
        assertNull(numberedList.getName());
    }

    void assertNameEquals(NumberedList numberedList, String name) {
        assertEquals(name,numberedList.getName());
    }

    void assertNoItems(NumberedList numberedList) {
        assertItemCount(numberedList,0);
    }

    void assertStart(NumberedList numberedList, int start) {
        assertEquals(start,numberedList.getStart());
    }

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

    @Test
    void constructorDefault() {
        NumberedList numberedList = new NumberedList();
        assertEquals(1,numberedList.getStart());
        assertEquals(0,numberedList.getItems().size());
        assertNull(numberedList.getName());
    }

    @Test
    void constructorName() {
        NumberedList numberedList = new NumberedList("name");
        assertEquals(1,numberedList.getStart());
        assertEquals(0,numberedList.getItems().size());
        assertEquals("name",numberedList.getName());
    }

    @Test
    void constructorStart() {
        NumberedList numberedList = new NumberedList(5);
        assertEquals(5,numberedList.getStart());
        assertEquals(0,numberedList.getItems().size());
        assertNull(numberedList.getName());
    }

    @Test
    void constructorNameStart() {
        NumberedList numberedList = new NumberedList("name",5);
        assertEquals(5,numberedList.getStart());
        assertEquals(0,numberedList.getItems().size());
        assertEquals("name",numberedList.getName());
    }

    @Test
    void constructorArray() {
        NumberedList numberedList = new NumberedList(new Object[] {"a","b"});
        assertEquals(1,numberedList.getStart());
        assertEquals(2,numberedList.getItems().size());
        assertNull(numberedList.getName());
    }

    @Test
    void constructorNameArray() {
        NumberedList numberedList = new NumberedList("test",new Object[] {"a","b"});
        assertEquals(1,numberedList.getStart());
        assertEquals(2,numberedList.getItems().size());
        assertEquals("test",numberedList.getName());
    }

    @Test
    void constructorStartArray() {
        NumberedList numberedList = new NumberedList(5,new Object[] {"a","b"});
        assertEquals(5,numberedList.getStart());
        assertEquals(2,numberedList.getItems().size());
        assertNull(numberedList.getName());
    }

    @Test
    void constructorNameStartArray() {
        NumberedList numberedList = new NumberedList("test",5,new Object[] {"a","b"});
        assertEquals(5,numberedList.getStart());
        assertEquals(2,numberedList.getItems().size());
        assertEquals("test",numberedList.getName());
    }

    @Test
    void constructorList() {
        NumberedList numberedList = new NumberedList(List.of("A", "B"));
        assertStart(numberedList,1);
        assertItemCount(numberedList,2);
        assertNullName(numberedList);
    }
    @Test
    void constructorStream() {
        NumberedList numberedList = new NumberedList(Stream.of("A", "B"));
        assertStart(numberedList,1);
        assertItemCount(numberedList,2);
        assertNullName(numberedList);
    }

    @Test
    void constructorStartStream() {
        NumberedList numberedList = new NumberedList(5,Stream.of("a","b"));
        assertStart(numberedList,5);
        assertNullName(numberedList);
        assertItemCount(numberedList,2);
    }

    @Test
    void constructorStartList() {
        NumberedList numberedList = new NumberedList(5,List.of("a","b"));
        assertStart(numberedList,5);
        assertNullName(numberedList);
        assertItemCount(numberedList,2);
    }

    @Test
    void constructorNameList() {
        NumberedList numberedList = new NumberedList("test",List.of("a","b"));
        assertStart(numberedList,1);
        assertNameEquals(numberedList,"test");
        assertItemCount(numberedList,2);
    }

    @Test
    void constructorNameStream() {
        NumberedList numberedList = new NumberedList("test",Stream.of("a","b"));
        assertStart(numberedList,1);
        assertNameEquals(numberedList,"test");
        assertItemCount(numberedList,2);
    }

    @Test
    void constructorNameStartList() {
        NumberedList numberedList = new NumberedList("test",5,List.of("a","b","c"));
        assertStart(numberedList,5);
        assertItemCount(numberedList,3);
        assertNameEquals(numberedList,"test");
    }

    @Test
    void constructorNameStartStream() {
        NumberedList numberedList = new NumberedList("test",5,Stream.of("a","b","c"));
        assertStart(numberedList,5);
        assertItemCount(numberedList,3);
        assertNameEquals(numberedList,"test");
    }
}
