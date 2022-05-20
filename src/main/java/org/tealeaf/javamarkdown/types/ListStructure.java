package org.tealeaf.javamarkdown.types;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ListStructure extends Structure {

    protected List<Object> objects = new ArrayList<>();

    protected String symbol;

    public ListStructure(String symbol, Object... objects) {
        this.symbol = symbol;
        add(objects);
    }

    @Override
    public String asString() {
        return String.format("%s", objects.stream().map(this::formatItem).collect(Collectors.joining("\n")));
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(objects.stream().map(this::formatItem).collect(Collectors.joining("\n")));
    }

    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    @Override
    protected void checkType(Object object) {
        super.checkType(object);
    }

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

    protected String formatItem(Object item) {
        return String.format("%s%s", symbol, indentItem(item.toString()));
    }

    protected String indentItem(String item) {
        return item.replace("\n", String.format("\n%s", " ".repeat(symbol.length())));
    }
}
