package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.NamedListStructure;

/**
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class NamedNumberedList extends NamedListStructure {

    private static final String FORMAT = "%s. ";

    private final int start;

    /**
     * Creates a new Named List with a name and an initial set of objects
     *
     * @param name    Name of the list
     * @param objects Set of initial objects
     * @since 0.0.11
     */
    public NamedNumberedList(String name, Object... objects) {
        super(name, objects);
        this.start = 1;
    }

    /**
     * <p>Creates a numbered list with indexing starting at the provided index</p>
     *
     * @param name    Name of the list
     * @param start   Number of the first element in the list
     * @param objects Initial set of items to include in the list
     *
     * @since 0.0.11
     */
    public NamedNumberedList(String name, int start, Object... objects) {
        super(name, objects);
        this.start = start;
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.11
     */
    @Override
    protected String getPrefix(int index) {
        return String.format(FORMAT, index + start);
    }
}
