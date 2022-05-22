package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

public class NumberedList extends ListStructure {
    @Deprecated
    private static final String SYMBOL = "1. ";

    private static final String FORMAT = "%s. ";

    private final int start;

    public NumberedList(Object... items) {
        super(items);
        start = 1;
    }

    public NumberedList(int start, Object... items) {
        super(items);
        this.start = start;
    }

    @Override
    protected String getPrefix(int index) {
        return String.format(FORMAT,index + start);
    }
}
