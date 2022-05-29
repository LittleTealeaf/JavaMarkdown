package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents an element that lists elements in a list-like structure
 * @author Thomas Kwashnak
 * @since 0.0.12
 */
public abstract class ListStructure extends Structure {

    protected final List<Object> items = new LinkedList<>();

    private final String name;

    public ListStructure() {
        name = null;
    }

    public ListStructure(String name) {
        this.name = name;
    }

    public ListStructure(Object[] objects) {
        name = null;
        add(objects);
    }

    public ListStructure(String name, Object[] objects) {
        this.name = name;
        add(objects);
    }

    protected abstract String getPrefix(int index);

    protected String printItem(int index) {
        String prefix = getPrefix(index);
        return prefix.concat(items.get(index).toString().replace("\n", String.format( "\n%s", " ".repeat(prefix.length()))));
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

    public ListStructure add(Object... objects) {
        for(Object object : objects) {
            items.add(checkType(object));
        }
        return this;
    }

    @Override
    protected <T> T checkType(T object) throws IllegalContentsException {
        return object instanceof ListStructure ? object : super.checkType(object);
    }
}
