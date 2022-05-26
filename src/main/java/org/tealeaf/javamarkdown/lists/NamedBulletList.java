package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.NamedListStructure;

/**
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class NamedBulletList extends NamedListStructure {

    private static final String PREFIX = "- ";

    /**
     * Creates a new Named List with a name and an initial set of objects
     *
     * @param name    Name of the list
     * @param objects Set of initial objects
     * @since 0.0.11
     */
    public NamedBulletList(String name, Object... objects) {
        super(name, objects);
    }

    /**
     * {@inheritDoc}
     * @since 0.0.11
     */
    @Override
    protected String getPrefix(int index) {
        return PREFIX;
    }
}
