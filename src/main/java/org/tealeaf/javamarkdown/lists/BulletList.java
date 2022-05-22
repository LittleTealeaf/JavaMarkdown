package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

/**
 * <p>Implements a bulleted list</p>
 * <p>The typical format of a bulleted list is:</p>
 * <pre>
 *     - Content
 *     - Content
 *     - Content
 * </pre>
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class BulletList extends ListStructure {

    private static final String PREFIX = "- ";

    /**
     * <p>Creates a new bulleted list with a preset list of objects</p>
     * @param items List of initial items to include
     */
    public BulletList(Object... items) {
        super(items);
    }

    /**
     * {@inheritDoc}
     * @param index Index of the item
     * @return {@code "- "}
     */
    @Override
    protected String getPrefix(int index) {
        return PREFIX;
    }
}
