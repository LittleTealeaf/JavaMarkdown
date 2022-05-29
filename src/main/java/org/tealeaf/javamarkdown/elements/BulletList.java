package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.ListStructure;

/**
 * <p>Implements a bulleted list</p>
 * <p>The typical format of a bulleted list is:</p>
 * <pre>
 *     - Content
 *     - Content
 *     - Content
 * </pre>
 * <p>The typical format of a bulleted list with a name is:</p>
 * <pre>
 *     Name
 *     - Content
 *     - Content
 * </pre>
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class BulletList extends ListStructure {

    private static final String PREFIX = "- ";

    /**
     * Creates an empty bullet list with no name
     * @since 0.0.12
     */
    public BulletList() {
        super();
    }

    /**
     * Creates a bullet list with an initial set of items and no name
     * @param objects The initial objects to add, in order
     * @since 0.0.12
     */
    public BulletList(Object[] objects) {
        super(objects);
    }

    /**
     * Creates an empty list with a name
     * @param name The string to print immediately before the list
     * @since 0.0.12
     */
    public BulletList(String name) {
        super(name);
    }

    /**
     * Creates a list with initial contents and a set name
     * @param name The string to print immediately before the list
     * @param objects The initial objects to add, in order
     * @since 0.0.12
     */
    public BulletList(String name, Object[] objects) {
        super(name,objects);
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
