package org.tealeaf.javamarkdown.types;

import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.CodeBlock;
import org.tealeaf.javamarkdown.elements.NumberedList;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ListStructureTest {

    @Test
    void requiresNewlineBeforeWithoutName() {
        assertTrue(new TestList().requiresNewlineBefore());
    }

    @Test
    void requiresNewLineBeforeWithName() {
        assertFalse(new TestList("test").requiresNewlineBefore());
    }

    @Test
    void requiresNewlineAfter() {
        assertTrue(new TestList().requiresNewlineAfter());
    }

    @Test
    void checkTypeIllegalStructure() {
        assertThrows(IllegalContentsException.class, () -> new TestList().checkType(new CodeBlock("test")));
    }

    @Test
    void checkTypeEmbeddedLists() {
        assertDoesNotThrow(() -> new TestList().checkType(new NumberedList()));
    }

    @Test
    void defaultConstructorNoName() {
        assertNull(new TestList().getName());
    }

    @Test
    void defaultConstructorNoItems() {
        assertEquals(0, new TestList().getItems().size());
    }

    @Test
    void nameConstructorSetsName() {
        assertEquals("test", new TestList("test").getName());
    }

    @Test
    void nameConstructorNoItems() {
        assertEquals(0, new TestList("test").getItems().size());
    }

    @Test
    void objectsConstructorNoName() {
        assertNull(new TestList(new Object[]{"a", "b"}).getName());
    }

    @Test
    void objectsConstructorSetsItems() {
        List<Object> items = List.of("a", "b", "c");
        TestList testList = new TestList(items.toArray());
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), testList.getItems().get(i));
        }
    }

    @Test
    void listConstructorNoName() {
        assertNull(new TestList(List.of("a", "b")).getName());
    }

    @Test
    void listConstructorSetsItems() {
        List<Object> items = List.of("a", "b", "c");
        TestList testList = new TestList(items);
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), testList.getItems().get(i));
        }
    }

    @Test
    void nameListConstructorSetsName() {
        assertEquals("test", new TestList("test", List.of("a", "b")).getName());
    }

    @Test
    void nameListConstructorSetsItems() {
        assertEquals(3, new TestList("tests", List.of("a", "b", "c")).getItems().size());
    }

    @Test
    void streamConstructorNoName() {
        assertNull(new TestList(Stream.of("a", "b")).getName());
    }

    @Test
    void streamConstructorSetsItems() {
        List<Object> items = List.of("a", "b", "c");
        TestList testList = new TestList(items.stream());
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), testList.getItems().get(i));
        }
    }

    @Test
    void nameStreamConstructorSetsName() {
        assertEquals("test", new TestList("test", Stream.of("a", "b", "c")).getName());
    }

    @Test
    void nameStreamConstructorSetsItems() {
        assertNotEquals(0, new TestList("test", Stream.of("A", "B", "C")).getItems().size());
    }

    @Test
    void addObject() {
        TestList testList = new TestList();
        testList.add("a");
        assertEquals("a", testList.getItems().get(0));
    }

    @Test
    void addStream() {
        TestList testList = new TestList();
        testList.add(Stream.of("a", "b"));
        assertEquals("a", testList.getItems().get(0));
        assertEquals("b", testList.getItems().get(1));
    }

    @Test
    void addObjectThrowsIllegal() {
        assertThrows(IllegalContentsException.class, () -> new TestList().add(new CodeBlock("test")));
    }

    @Test
    void addStreamThrowsIllegal() {
        assertThrows(IllegalContentsException.class, () -> new TestList().add(Stream.of(new CodeBlock("test"))));
    }

    @Test
    void checkTypeThrowsStructures() {
        assertThrows(IllegalContentsException.class, () -> new TestList().checkType(new CodeBlock("test")));
    }

    @Test
    void checkTypeIgnoresListStructure() {
        assertDoesNotThrow(() -> new TestList().checkType(new TestList()));
    }

    static class TestList extends ListStructure {

        public TestList() {
            super();
        }

        public TestList(String name) {
            super(name);
        }

        public TestList(Object[] objects) {
            super(objects);
        }

        public TestList(List<?> list) {
            super(list);
        }

        public TestList(String name, List<?> list) {
            super(name, list);
        }

        public TestList(Stream<?> stream) {
            super(stream);
        }

        public TestList(String name, Stream<?> stream) {
            super(name, stream);
        }

        public TestList(String name, Object[] objects) {
            super(name, objects);
        }

        @Override
        protected String getPrefix(int index) {
            return null;
        }
    }
}