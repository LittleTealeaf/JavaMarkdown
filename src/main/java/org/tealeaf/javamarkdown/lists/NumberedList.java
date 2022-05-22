package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

/**
 * <p>Implements a numbered list. Allows adding items, which will be formatted and printed into a list</p>
 * <p>By default, the lists format as the following</p>
 * <pre>
 *     1. Content
 *     2. Content
 *     3. Content
 * </pre>
 * <p>However, by using {@link #NumberedList(int, Object...)}, it is possible to specify the first index so the following can be possible</p>
 * <pre>
 *     3. Content
 *     4. Content
 *     5. Content
 * </pre>
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class NumberedList extends ListStructure {

    private static final String FORMAT = "%s. ";

    private final int start;

    /**
     * <p>Creates a numbered list with indexing starting at 1.</p>
     * @param items Initial set of items to include in the list
     * @since 0.0.11
     */
    public NumberedList(Object... items) {
        super(items);
        start = 1;
    }

    /**
     * <p>Creates a numbered list with indexing starting at the provided index</p>
     * @param start Number of the first element in the list
     * @param items Initial set of items to include in the list
     * @since 0.0.11
     */
    public NumberedList(int start, Object... items) {
        super(items);
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
