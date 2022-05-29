package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents an element that lists elements in a list-like structure
 * @author Thomas Kwashnak
 * @since 0.0.12
 */
public abstract class ListElement extends Structure {

    private final String NEWLINE_FORMAT = "\n%s";

    protected final List<Object> items = new LinkedList<>();

    private final String name;

    public ListElement() {
        name = null;
    }

    public ListElement(String name) {
        this.name = name;
    }

    public ListElement(Object[] objects) {
        name = null;
        add(objects);
    }

    public ListElement(String name, Object[] objects) {
        this.name = name;
        add(objects);
    }

    protected abstract String getPrefix(int index);

    protected String printItem(int index) {
        String prefix = getPrefix(index);
        return prefix.concat(items.get(index).toString().replace("\n", String.format(NEWLINE_FORMAT, " ".repeat(prefix.length()))));
    }

    @Override
    public String asString() {
        return IntStream.range(0,items.size()).mapToObj(this::printItem).collect(Collectors.joining("\n"));
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(asString());
    }

    @Override
    public boolean requiresNewlineBefore() {
        return name != null;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    public ListElement add(Object... objects) {
        for(Object object : objects) {
            items.add(checkType(object));
        }
        return this;
    }

    @Override
    protected <T> T checkType(T object) throws IllegalContentsException {
        return object instanceof ListElement ? object : super.checkType(object);
    }
}
