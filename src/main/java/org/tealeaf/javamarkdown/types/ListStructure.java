package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;

import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>Abstract structure that represents an element that lists elements in a list-like structure</p>
 * <p>Handles adding items and printing the final result. Classes extending this provide the prefix structure used in the list through {@link #getPrefix(int)}</p>
 * <p>Additionally, the option to add a name to the list provides easy nesting of lists. For this reason, objects that extend this class are allowed to be added. Adding a
 * name field through one of the named constructors ({@link #ListStructure(String)} or {@link #ListStructure(String, Object[])}) will remove the before-newline requirement
 * (see {@link #requiresNewlineBefore()})
 * </p>
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public abstract class ListStructure extends Structure {

    /**
     * List of objects to print, in order that they are printed
     * @since 0.0.11
     */
    protected final List<Object> items = new LinkedList<>();

    /**
     * Name to print before printing the list
     * @since 0.0.12
     */
    protected final String name;

    /**
     * Creates an empty list with no name
     * @since 0.0.12
     */
    public ListStructure() {
        name = null;
    }

    /**
     * Creates an empty list with a name
     * @param name The string to print immediately before the list
     * @since 0.0.12
     */
    public ListStructure(String name) {
        this.name = name;
    }

    /**
     * Creates a list with initial contents and no name
     * @param objects The initial objects to add, in order
     * @since 0.0.12
     */
    public ListStructure(Object[] objects) {
        name = null;
        add(objects);
    }

    /**
     * Creates a list with initial contents and a set name
     * @param name The string to print immediately before the list
     * @param objects The initial objects to add, in order
     * @since 0.0.12
     */
    public ListStructure(String name, Object[] objects) {
        this.name = name;
        add(objects);
    }

    /**
     * <p>Generates the prefix to insert before a given item in the list.</p>
     * @param index Item's index to get the prefix for
     * @return Prefix to insert before the item at the provided index
     * @since 0.0.11
     */
    protected abstract String getPrefix(int index);

    /**
     * <p>Formats and prints an item at the provided index.</p>
     * <p>Handles multi-lined items by indenting each item a number of spaces equal to the prefix's length</p>
     * @param index Item's index to print
     * @return Formatted and prefixed item in the list
     * @since 0.0.11
     */
    protected String printItem(int index) {
        final String prefix = getPrefix(index);
        final String newlineIndent = "\n".concat(" ".repeat(prefix.length()));
        return prefix.concat(items.get(index).toString().replace("\n", newlineIndent));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asString() {
        String list = IntStream.range(0,items.size()).mapToObj(this::printItem).collect(Collectors.joining("\n"));
        if(name != null) {
            return name.concat("\n").concat(list);
        } else {
            return list;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(asString());
    }

    /**
     * {@inheritDoc}
     * @return {@code true} if there was no name provided, {@code false} if a name is provided
     * @since 0.0.12
     */
    @Override
    public boolean requiresNewlineBefore() {
        return name == null;
    }

    /**
     * {@inheritDoc}
     * @return {@code true}
     * @since 0.0.11
     */
    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    /**
     * <p>Adds objects to the end of the list.</p>
     * @param objects Set of objects to append to the end of the list
     * @return A reference to this list structure
     * @since 0.0.12
     */
    public ListStructure add(Object... objects) {
        for(Object object : objects) {
            items.add(checkType(object));
        }
        return this;
    }

    /**
     * {@inheritDoc}
     * <p>Overridden to specifically allow other instances of {@link ListStructure ListStructures}, allowing nesting other lists.</p>
     */
    @Override
    protected <T> T checkType(T object) throws IllegalContentsException {
        return object instanceof ListStructure ? object : super.checkType(object);
    }
}
