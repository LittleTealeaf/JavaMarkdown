package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Structure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Table extends Structure {

    private Object[] headers;
    private Alignment[] alignments;

    private final List<Object[]> values = new LinkedList<>();

    public Table() {

    }

    public Table setHeaders(Object... headers) {

        this.headers = Stream.of(headers).map(this::checkType).toArray();
        Arrays.fill(alignments = new Alignment[headers.length], Alignment.CENTER);
        return this;
    }

    public Table setAlignments(Alignment... alignments) {
        this.alignments = alignments;
        return this;
    }

    public Table addRows(Object[]... rows) {
        Stream.of(rows).forEach(row -> values.add(Stream.of(row).map(this::checkType).toArray()));
        return this;
    }

    @Override
    public String asString() {
        List<Stream<String>> streams = new LinkedList<>(List.of(Stream.of(headers).map(Object::toString), Stream.of(alignments).map(Alignment::getAlignment)));
        values.forEach(i -> streams.add(Stream.of(i).map(Object::toString)));
        return String.format("| %s |", streams.stream().map(s -> s.collect(Collectors.joining(" | "))).collect(Collectors.joining(" |\n| ")));
    }

    /**
     * {@inheritDoc}
     * @since 0.0.18
     * @return {@code true}
     */
    @Override
    public boolean requiresNewlineBefore() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @since 0.0.18
     * @return {@code true}
     */
    @Override
    public boolean requiresNewlineAfter() {
        return true;
    }

    public enum Alignment {
        LEFT(":--"),
        CENTER(":-:"),
        RIGHT("--:");

        final String alignment;

        Alignment(String alignment) {
            this.alignment = alignment;
        }

        public String getAlignment() {
            return alignment;
        }

        @Override
        public String toString() {
            return alignment;
        }
    }
}
