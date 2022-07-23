package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.exceptions.IllegalAlignmentCountException;
import org.tealeaf.javamarkdown.types.Structure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Renders a Table in Markdown, including Headers and row-by-row contents
 * </p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.18
 */
public class Table extends Structure {

    private Object[] headers;
    private Alignment[] alignments;

    private final List<Object[]> values = new LinkedList<>();

    /**
     * <p>
     * Creates a new {@code Table} object
     * </p>
     * @since 0.0.18
     */
    public Table() {

    }

    /**
     * <p>
     * Sets the headers and defaults the alignments to all be
     * {@link Alignment#CENTER CENTER}
     * </p>
     *
     * @since 0.0.18
     * @param headers Array of header objects
     * @return This table
     */
    public Table setHeaders(Object... headers) {

        this.headers = Stream.of(headers).map(this::checkType).toArray();
        Arrays.fill(alignments = new Alignment[headers.length], Alignment.CENTER);
        return this;
    }

    /**
     * <p>
     * Sets the alignments for the table
     * </p>
     * <p>
     * Throws {@link IllegalAlignmentCountException} if the number of alignments
     * does not match the number of headers
     * </p>
     *
     * @param alignments Column alignments in order
     * @return This table
     */
    public Table setAlignments(Alignment... alignments) {
        if (headers != null && alignments.length != headers.length) {
            throw new IllegalAlignmentCountException(headers.length, alignments.length);
        }

        this.alignments = alignments;
        return this;
    }

    /**
     * Adds rows of content to the table
     *
     * @since 0.0.18
     * @param rows Arrays of cell contents for each row
     * @return This table
     */
    public Table addRows(Object[]... rows) {
        Stream.of(rows).forEach(row -> values.add(Stream.of(row).map(this::checkType).toArray()));
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @since 0.0.18
     */
    @Override
    public String asString() {
        List<Stream<String>> streams = new LinkedList<>(
                List.of(Stream.of(headers).map(Object::toString), Stream.of(alignments).map(Alignment::getAlignment)));
        values.forEach(i -> streams.add(Stream.of(i).map(Object::toString)));
        return String.format("| %s |",
                streams.stream().map(s -> s.collect(Collectors.joining(" | "))).collect(Collectors.joining(" |\n| ")));
    }

    /**
     * <p>
     * Represents the alignment value used for columns in a {@link Table}
     * </p>
     *
     * @author Thomas Kwashnak
     * @since 0.0.18
     */
    public enum Alignment {
        /**
         * <p>Aligns all text to the left in the cell.</p>
         * <p>This is the default alignment</p>
         * @since 0.0.18
         */
        LEFT(":--"),
        /**
         * <p>Aligns all text to the center of the cell.</p>
         * @since 0.0.18
         */
        CENTER(":-:"),
        /**
         * <p>Aligns all text to the right in the cell</p>
         * @since 0.0.18
         */
        RIGHT("--:");

        final String alignment;

        Alignment(String alignment) {
            this.alignment = alignment;
        }

        /**
         * <p>
         * Returns the printed text for this alignment
         * </p>
         *
         * @since 0.0.18
         *
         * @return Markdown text for this alignment
         */
        public String getAlignment() {
            return alignment;
        }

        /**
         * @since 0.0.18
         * @return The Markdown Text for this alignment
         */
        @Override
        public String toString() {
            return alignment;
        }
    }
}
