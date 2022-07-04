package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.Tests;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    Table table;

    @BeforeEach
    void beforeNewTable() {
        table = new Table();
    }

    @Test
    void setHeadersReturnsTable() {
        assertSame(table,table.setHeaders(Stream.of(Tests.randomWords()).toArray()));
    }

    @Test
    void setAlignmentsReturnsTable() {
        assertSame(table,table.setAlignments(Table.Alignment.LEFT, Table.Alignment.RIGHT, Table.Alignment.CENTER));
    }

    @Test
    void addRowsReturnsTable() {
        assertSame(table,table.addRows(Stream.of(Tests.randomWords()).toArray()));
    }

    @Test
    void setHeadersContainsHeaders() {
        Object[] headers = new Object[]{
                Tests.randomWord(), Tests.randomWord(), Tests.randomWord()
        };
        table.setHeaders(headers);

        Stream.of(headers).forEach(header -> {
            assertTrue(table.asString().contains(header.toString()));
        });
    }

    @Test
    void setHeadersDefaultsAlignments() {
        Object[] headers = new Object[]{
                Tests.randomWord(), Tests.randomWord(), Tests.randomWord()
        };
        table.setHeaders(headers);
        assertEquals(headers.length + 1,table.asString().split(Table.Alignment.CENTER.toString()).length);
    }

    @Test
    void setHeadersOverridesAlignments() {
        Object[] headers = new Object[] {
                Tests.randomWord(),Tests.randomWord(),Tests.randomWord()
        };
        table.setAlignments(Table.Alignment.LEFT, Table.Alignment.RIGHT, Table.Alignment.LEFT).setHeaders(headers);
        assertFalse(table.asString().contains(Table.Alignment.LEFT.toString()));
        assertFalse(table.asString().contains(Table.Alignment.RIGHT.toString()));
    }

    @Test
    void requiresNewLineAfter() {
        assertTrue(table.requiresNewlineAfter());
    }

    @Test
    void requiresNewLineBefore() {
        assertTrue(table.requiresNewlineBefore());
    }




}