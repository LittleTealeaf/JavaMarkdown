package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

public class NumberedList extends ListStructure {
    private static final String SYMBOL = "1. ";

    public NumberedList(Object... objects) {
        super(SYMBOL, objects);
    }
}
