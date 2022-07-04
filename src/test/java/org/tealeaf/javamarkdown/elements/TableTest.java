package org.tealeaf.javamarkdown.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void testPrint() {
        Table table = new Table(new String[] {"a","b","c"});
        table.appendRow("a","B","C");
        System.out.println(table.asString());
    }
}