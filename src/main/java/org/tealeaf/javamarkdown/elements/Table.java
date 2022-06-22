package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Structure;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Table extends Structure {

    private Object[] headers;
    private Alignment[] alignments;

    private final List<Object[]> values = new LinkedList<>();

    public Table(Object[] headers) {
        this.headers = headers;
        this.alignments = new Alignment[headers.length];
        IntStream.range(0,alignments.length).parallel().forEach(i -> alignments[i] = Alignment.CENTER);
    }

    public void appendRow(Object... objects) {
        Stream.of(objects).forEach(this::checkType);
        values.add(objects);
    }


    @Override
    public String asString() {
        List<Stream<String>> streams = new LinkedList<>(List.of(Stream.of(headers).map(Object::toString),Stream.of(alignments).map(Alignment::getAlignment)));
        values.forEach(i -> streams.add(Stream.of(i).map(Object::toString)));
        return String.format("|%s|", streams.stream().map(s -> s.collect(Collectors.joining(" | "))).collect(Collectors.joining("|\n|")));
    }

    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    public enum Alignment {
        LEFT(":--"),CENTER(":-:"),RIGHT("--:");

        final String alignment;
        Alignment(String alignment) {
            this.alignment = alignment;
        }

        public String getAlignment() {
            return alignment;
        }
    }
}
