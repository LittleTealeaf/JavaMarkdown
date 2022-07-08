package org.tealeaf.javamarkdown.exceptions;

/**
 * <p>
 * When thrown, indicates that the number of alignments passed into a table did
 * not match the number of headers
 * </p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.18
 */
public class IllegalAlignmentCountException extends RuntimeException {

    private final int expected, found;

    /**
     * <p>Creates a new IllegalAlignmentCountException with the expected count and the actual count</p>
     * @since 0.0.18
     * @param expected Number of alignments expected
     * @param found Number of alignments found
     */
    public IllegalAlignmentCountException(int expected, int found) {
        this.expected = expected;
        this.found = found;
    }

    /**
     * {@inheritDoc}
     * @since 0.0.18
     */
    @Override
    public String getMessage() {
        return String.format("Illegal Alignment Count: Expected %d alignemnts, found %d", expected, found);
    }

}
