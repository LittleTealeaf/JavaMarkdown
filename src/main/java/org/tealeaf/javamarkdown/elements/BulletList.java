package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.ListStructure;

import java.util.List;
import java.util.stream.Stream;

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
 *
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class BulletList extends ListStructure {

    private static final String PREFIX = "- ";

    /**
     * Creates an empty bullet list with no name
     *
     * @since 0.0.12
     */
    public BulletList() {
        super();
    }

    /**
     * Creates a bullet list with an initial set of items and no name
     *
     * @param objects The initial objects to add, in order
     *
     * @since 0.0.12
     */
    public BulletList(Object[] objects) {
        super(objects);
    }

    /**
     * Creates an empty list with a name
     *
     * @param name The string to print immediately before the list
     *
     * @since 0.0.12
     */
    public BulletList(String name) {
        super(name);
    }

    /**
     * Creates a list with initial contents and a set name
     *
     * @param name    The string to print immediately before the list
     * @param objects The initial objects to add, in order
     *
     * @since 0.0.12
     */
    public BulletList(String name, Object[] objects) {
        super(name, objects);
    }

    /**
     * Creates a list with initial contents from a stream
     *
     * @param stream Stream of objects to insert into the list
     *
     * @since 0.0.15
     */
    public BulletList(Stream<?> stream) {
        super(stream);
    }

    /**
     * Creates a list with a name and initial contents from a stream
     *
     * @param name   The string to print immediately before the list
     * @param stream Stream of objects to insert into the list
     *
     * @since 0.0.15
     */
    public BulletList(String name, Stream<?> stream) {
        super(name, stream);
    }

    /**
     * Creates a list with initial contents from a list
     *
     * @param objects List of objects to include in the list
     *
     * @since 0.0.15
     */
    public BulletList(List<?> objects) {
        super(objects);
    }

    /**
     * Creates a list with a set name and initial contents from a list
     *
     * @param name    String to print immediately before the list
     * @param objects List of objects to include in the list
     *
     * @since 0.0.15
     */
    public BulletList(String name, List<?> objects) {
        super(name, objects);
    }

    /**
     * {@inheritDoc}
     *
     * @param index Index of the item
     *
     * @return {@code "- "}
     * @since 0.0.11
     */
    @Override
    protected String getPrefix(int index) {
        return PREFIX;
    }
}
