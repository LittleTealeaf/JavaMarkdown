package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.ListStructure;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>Implements a numbered list. Allows adding items, which will be formatted and printed into a list</p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public class NumberedList extends ListStructure {

    private static final String FORMAT = "%s. ";

    private final int start;

    /**
     * <p>Creates an empty list with no name.</p>
     * <p>Items must be added using {@link #add(Object...)}</p>
     * <p>Once added, the list will be structured as the following</p>
     * <pre>
     *     1. Content
     *     2. Content
     *     3. Content
     * </pre>
     * <p>With a required newline before and after the list</p>
     *
     * @since 0.0.12
     */
    public NumberedList() {
        super();
        start = 1;
    }

    /**
     * <p>Creates an empty list with a set name.</p>
     * <p>Items must be added using {@link #add(Object...)}</p>
     * <p>Once added, the list will be structured as the following</p>
     * <pre>
     *     List Name
     *     1. Content
     *     2. Content
     *     3. Content
     * </pre>
     * <p>With a required newline after but not before the list.</p>
     *
     * @param name The list name to include before the list
     *
     * @since 0.0.12
     */
    public NumberedList(String name) {
        super(name);
        start = 1;
    }

    /**
     * <p>Creates an empty list with no name and a set starting digit.</p>
     * <p>Items must be added using {@link #add(Object...)}</p>
     * <p>Once added, the list will be structured like the following:</p>
     * <pre>
     *     4. Content
     *     5. Content
     *     6. Content
     * </pre>
     * <p>With a required newline before and after the list</p>
     * <p>The first digit is the digit set up in the parameters</p>
     *
     * @param start The number of the first item in the list
     *
     * @since 0.0.12
     */
    public NumberedList(int start) {
        super();
        this.start = start;
    }

    /**
     * <p>Creates an empty list with a set name and set starting digit.</p>
     * <p>Items must be added using {@link #add(Object...)}</p>
     * <p>Once added, the list will be structured like the following:</p>
     * <pre>
     *     List Name
     *     4. Content
     *     5. Content
     *     6. Content
     * </pre>
     * <p>with a required newline after but not before the list</p>
     * <p>The first digit is the digit set up in the parameters</p>
     *
     * @param name  The list name to include before the list
     * @param start The number of the first item in the list
     *
     * @since 0.0.12
     */
    public NumberedList(String name, int start) {
        super(name);
        this.start = start;
    }

    /**
     * <p>Creates a list with initial objects but no set name</p>
     * <p>Items can still be added using {@link #add(Object...)}</p>
     * <p>The list will be structured like the following:</p>
     * <pre>
     *     1. Content
     *     2. Content
     *     3. Content
     * </pre>
     * <p>with a required newline before and after the list</p>
     *
     * @param objects Initial objects to include in the list
     *
     * @since 0.0.12
     */
    public NumberedList(Object[] objects) {
        super(objects);
        this.start = 1;
    }

    /**
     * <p>Creates a list with a set name and initial objects</p>
     * <p>Items can still be added to the end using {@link #add(Object...)}</p>
     * <p>The list will be structured like the following:</p>
     * <pre>
     *     List Name
     *     1. Content
     *     2. Content
     *     3. Content
     * </pre>
     * <p>With a required newline after but not before the list</p>
     *
     * @param name    The list name to include before the list
     * @param objects Initial objects to include in the list
     *
     * @since 0.0.12
     */
    public NumberedList(String name, Object[] objects) {
        super(name, objects);
        this.start = 1;
    }

    /**
     * <p>Creates a list with a set starting index and initial objects</p>
     * <p>Items can still be added to the end using {@link #add(Object...)}</p>
     * <p>The items will be structured like the following:</p>
     * <pre>
     *     4. Content
     *     5. Content
     *     6. Content
     * </pre>
     * <p>With a required newline before and after the list</p>
     * <p>The first digit is the digit set up in the parameters</p>
     *
     * @param start   The number of the first item in the list
     * @param objects Initial objects to include in the list
     *
     * @since 0.0.12
     */
    public NumberedList(int start, Object[] objects) {
        super(objects);
        this.start = start;
    }

    /**
     * <p>Creates a list with a name, set starting index, and initial objects</p>
     * <p>Items can still be added to the end using {@link #add(Object...)}</p>
     * <p>The items will be structured like the following:</p>
     * <pre>
     *     List Name
     *     4. Content
     *     5. Content
     *     6. Content
     * </pre>
     * <p>With a required newline after but not before the list</p>
     * <p>The first digit is the digit set up in the parameters</p>
     *
     * @param name    The list name to include before the list
     * @param start   The number of the first item in the list
     * @param objects Initial objects to include in the list
     *
     * @since 0.0.12
     */
    public NumberedList(String name, int start, Object[] objects) {
        super(name, objects);
        this.start = start;
    }

    public NumberedList(List<?> objects) {
        super(objects);
        this.start = 1;
    }

    public NumberedList(Stream<?> stream) {
        super(stream);
        this.start = 1;
    }

    public NumberedList(int start, List<?> objects) {
        super(objects);
        this.start = start;
    }

    public NumberedList(int start, Stream<?> stream) {
        super(stream);
        this.start = start;
    }

    public NumberedList(String name, List<?> objects) {
        super(name, objects);
        this.start = 1;
    }

    public NumberedList(String name, Stream<?> stream) {
        super(name, stream);
        this.start = 1;
    }

    public NumberedList(String name, int start, List<?> objects) {
        super(name, objects);
        this.start = start;
    }

    public NumberedList(String name, int start, Stream<?> stream) {
        super(name, stream);
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

    public int getStart() {
        return start;
    }
}
