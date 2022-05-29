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


    public BulletList() {
        super();
    }

    public BulletList(Object[] objects) {
        super(objects);
    }

    public BulletList(String name) {
        super(name);
    }

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
