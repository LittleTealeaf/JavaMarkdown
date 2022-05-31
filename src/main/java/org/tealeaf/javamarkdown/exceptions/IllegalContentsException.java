package org.tealeaf.javamarkdown.exceptions;

import org.tealeaf.javamarkdown.elements.Italic;
import org.tealeaf.javamarkdown.elements.NumberedList;

/**
 * <p>
 * When thrown, indicates that an illegal element was attempted to be passed as content of another element. For example, if a
 * {@link NumberedList NumberedList} was passed into a {@link Italic Italic} element, then this error would
 * be thrown
 * </p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.8
 */
public class IllegalContentsException extends RuntimeException {

    private final Class<?> illegalClass;

    /**
     * <p>Creates a new IllegalContents exception with the illegal class in question</p>
     *
     * @param illegalClass The illegal class passed as contents
     * @since 0.0.8
     */
    public IllegalContentsException(Class<?> illegalClass) {
        this.illegalClass = illegalClass;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return  the detail message string of this {@code Throwable} instance.
     * @since 0.0.8
     */
    @Override
    public String getMessage() {
        return String.format("Illegal Class class passed into object: %s", illegalClass.toString());
    }
}
