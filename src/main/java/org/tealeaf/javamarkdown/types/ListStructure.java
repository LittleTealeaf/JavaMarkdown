package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;

import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public abstract class ListStructure extends Structure {

    protected List<Object> objects = new ArrayList<>();

    protected String symbol;

    public ListStructure(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String asString() {
        return String.format("\n%s", objects.stream().map(this::formatItem).collect(Collectors.joining("\n")));
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        writer.write('\n');
        objects.stream().map(this::formatItem).forEach(item -> {
            try {
                writer.append(item).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return writer;
    }

    @Override
    protected void checkType(Object object) throws IllegalContentsException {
        super.checkType(object);
    }

    public ListStructure addItem(Object object) throws IllegalContentsException {
        checkType(object);
        objects.add(object);
        return this;
    }

    protected String formatItem(Object item) {
        return String.format("%s%s", symbol,indentItem(item.toString()));
    }

    protected String indentItem(String item) {
        String indent = String.format("\n%s", " ".repeat(symbol.length()));
        return item.replace("\n",indent);
    }

}
