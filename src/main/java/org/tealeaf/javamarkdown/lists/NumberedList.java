package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

///**
// * <p>Implements a numbered list. Allows adding items, which will be formatted and printed into a list</p>
// * <p>By default, the lists format as the following</p>
// * <pre>
// *     1. Content
// *     2. Content
// *     3. Content
// * </pre>
// * <p>However, by using {@link #NumberedList(int, Object...)}, it is possible to specify the first index so the following can be possible</p>
// * <pre>
// *     3. Content
// *     4. Content
// *     5. Content
// * </pre>
// * @author Thomas Kwashnak
// * @since 0.0.11
// */
public class NumberedList extends ListStructure {

    private static final String FORMAT = "%s. ";

    private final int start;

    public NumberedList() {
        super();
        start = 1;
    }

    public NumberedList(String name) {
        super(name);
        start = 1;
    }

    public NumberedList(int start) {
        super();
        this.start = start;
    }

    public NumberedList(String name, int start) {
        super(name);
        this.start = start;
    }

    public NumberedList(Object[] objects) {
        super(objects);
        this.start = 1;
    }

    public NumberedList(String name, Object[] objects) {
        super(name,objects);
        this.start = 1;
    }

    public NumberedList(int start, Object[] objects) {
        super(objects);
        this.start = start;
    }

    public NumberedList(String name, int start, Object[] objects) {
        super(name,objects);
        this.start = start;
    }


    /**
     * {@inheritDoc}
     * @since 0.0.11
     */
    @Override
    protected String getPrefix(int index) {
        return String.format(FORMAT,index + start);
    }
}
