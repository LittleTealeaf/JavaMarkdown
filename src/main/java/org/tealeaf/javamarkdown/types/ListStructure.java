package org.tealeaf.javamarkdown.types;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Represents a structure that lists elements in a list-like structure
 * @author Thomas Kwashnak
 * @since 0.0.7
 *
 */
public class ListStructure extends Structure {

    /**
     * List of objects to include
     */
    protected List<Object> objects = new ArrayList<>();

    /**
     * Symbol to put in front of each item
     */
    @Deprecated
    protected String symbol;

    public ListStructure(Object... items) {

    }



    /**
     * @deprecated use {@link #ListStructure(Object...)}
     * @param symbol
     * @param objects
     */
    @Deprecated
    public ListStructure(String symbol, Object... objects) {
        this.symbol = symbol;
        add(objects);
    }

    /**
     * {@inheritDoc}
     * @return Markdown Element as String
     * @since 0.0.7
     */
    @Override
    public String asString() {
        return String.format("%s", objects.stream().map(this::formatItem).collect(Collectors.joining("\n")));
    }

    /**
     * {@inheritDoc}
     * @param writer Writer to write contents to
     *
     * @since 0.0.7
     */
    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(objects.stream().map(this::formatItem).collect(Collectors.joining("\n")));
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
            checkType(object);
            this.objects.add(object);
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
        checkType(object);
        objects.add(object);
        return this;
    }

    /**
     * <p>Formats a single item as an element in the list</p>
     * <p>Uses the provided symbol and indents the item using {@link #indentItem(String)}</p>
     * @param item Item to format into the list
     * @return A string with the formatted item
     * @deprecated Use new format of formatting items
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
     * @deprecated Use new format for formatting items
     * @since 0.0.7
     */
    @Deprecated
    protected String indentItem(String item) {
        return item.replace("\n", String.format("\n%s", " ".repeat(symbol.length())));
    }
}