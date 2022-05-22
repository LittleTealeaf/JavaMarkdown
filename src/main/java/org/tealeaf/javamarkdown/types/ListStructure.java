package org.tealeaf.javamarkdown.types;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * Represents a structure that lists elements in a list-like structure
 * @author Thomas Kwashnak
 * @since 0.0.7
 *
 */
public abstract class ListStructure extends Structure {

    /**
     * @deprecated Changed to {@link #items}
     */
    @Deprecated
    protected List<Object> objects = new ArrayList<>();

    /**
     * List of items within the list
     * @since 0.0.11
     */
    protected final List<Object> items = new LinkedList<>();

    /**
     * Symbol to put in front of each item
     * @deprecated Static symbols are no longer used, implement symbols using {@link #getPrefix(int)}
     */
    @Deprecated
    protected String symbol;

    /**
     * Creates a new ListStructure, initialized with the provided items
     * @param items Items to add to the list structure.
     * @since 0.0.11
     */
    public ListStructure(Object... items) {
        add(items);
    }



    /**
     * @deprecated Static symbols are no longer Implemented. Instead, use {@link #ListStructure(Object...)} and define the symbol in {@link #getPrefix(int)}
     * @param symbol ?
     * @param objects ?
     */
    @Deprecated
    public ListStructure(String symbol, Object... objects) {
        this.symbol = symbol;
        add(objects);
    }

    protected abstract String getPrefix(int index);

    /**
     * <p>Prints an item using the {@link #getPrefix(int)} to determine the prefix to use.</p>
     * @param index Item index to use
     * @return Formatted Item as a String
     * @since 0.0.11
     */
    protected String printItem(int index) {
        String prefix = getPrefix(index);
        return String.format("%s%s",prefix,items.get(index).toString().replace("\n", String.format("\n%s", " ".repeat(prefix.length()))));
    }

    /**
     * {@inheritDoc}
     * @return Markdown Element as String
     * @since 0.0.7
     */
    @Override
    public String asString() {
        return IntStream.range(0,items.size()).mapToObj(this::printItem).collect(Collectors.joining("\n"));
    }

    /**
     * {@inheritDoc}
     * @param writer Writer to write contents to
     *
     * @since 0.0.7
     */
    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(IntStream.range(0,items.size()).mapToObj(this::printItem).collect(Collectors.joining("\n")));
    }

    /**
     * {@inheritDoc}
     * @return {@code true}
     * @since 0.0.7
     */
    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @return {@code true}
     * @since 0.0.7
     */
    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    /**
     * <p>Adds an element to the end of the list</p>
     * @param objects List of objects to append to the end of the list
     * @return A reference to this ListStructure
     * @since 0.0.11
     */
    public ListStructure add(Object... objects) {
        for (Object object : objects) {
            this.items.add(checkType(object));
        }
        return this;
    }

    /**
     * @param object ?
     *
     * @return ?
     *
     * @deprecated Use {@link #add(Object...)} instead
     */
    @Deprecated
    public ListStructure addItem(Object object) {
        return add(object);
    }

    /**
     * <p>Formats a single item as an element in the list</p>
     * <p>Uses the provided symbol and indents the item using {@link #indentItem(String)}</p>
     * @param item Item to format into the list
     * @return A string with the formatted item
     * @deprecated Implemented as {@link #printItem(int)}
     * @since 0.0.7
     */
    @Deprecated
    protected String formatItem(Object item) {
        return String.format("%s%s", symbol, indentItem(item.toString()));
    }

    /**
     * <p>Idents an item to properly fit in the list</p>
     * <p>Takes each {@code \n} and adds spaces after it to indent it to the indentation required by the symbol</p>
     * @param item String to format
     * @return String formatted to the indentation of the item
     * @deprecated Implemented in {@link #printItem(int)}
     * @since 0.0.7
     */
    @Deprecated
    protected String indentItem(String item) {
        return item.replace("\n", String.format("\n%s", " ".repeat(symbol.length())));
    }
}