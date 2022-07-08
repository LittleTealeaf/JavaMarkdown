package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.javamarkdown.elements.Table.Alignment;
import org.tealeaf.javamarkdown.exceptions.IllegalAlignmentCountException;

import test.Tests;

import java.util.Arrays;
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
    void setCorrectAlignmentCountDoesNotThrowException() {
        int count = Tests.randomInteger(1,20);
        Object[] headers = new Object[count];
        Arrays.fill(headers, Tests.randomWord());
        Alignment[] alignments = new Alignment[count];
        Arrays.fill(alignments, Alignment.LEFT);
        assertDoesNotThrow(() -> table.setHeaders(headers).setAlignments(alignments));
    }

    @Test
    void setIncorrectAlignmentCountThrowsException() {
        // check under
        int headerCount = Tests.randomInteger(2,50);
        Object[] headers = new Object[headerCount];
        Arrays.fill(headers, Tests.randomWord());
        Alignment[] underAlignments = new Alignment[Tests.randomInteger(1,headers.length)];
        Arrays.fill(underAlignments, Alignment.LEFT);
        Alignment[] overAlignments = new Alignment[Tests.randomInteger(headers.length + 1,100)];
        Arrays.fill(overAlignments, Alignment.RIGHT);
        assertThrows(IllegalAlignmentCountException.class, () -> table.setHeaders(headers).setAlignments(underAlignments));
        assertThrows(IllegalAlignmentCountException.class, () -> table.setHeaders(headers).setAlignments(overAlignments));
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
