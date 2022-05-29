package org.tealeaf.javamarkdown.exceptions;

/**
 * Indicates that an illegal header level was passed into the {@link org.tealeaf.javamarkdown.elements.Header} class
 * @author Thomas Kwashnak
 * @since 0.0.12
 */
public class IllegalHeaderLevelException extends RuntimeException {

    /**
     * Creates a new IllegalHeaderLevelException with a specified level
     * @param level The illegal level passed
     * @since 0.0.12
     */
    public IllegalHeaderLevelException(int level) {
        super(String.format("Illegal Header Level: %s is not within the range [1-6]", level));
    }
}
