package org.tealeaf.javamarkdown;

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
 * @deprecated Exception has been moved into the exceptions package. Please switch to using {@link org.tealeaf.javamarkdown.exceptions.IllegalContentsException}
 */
@Deprecated
public class IllegalContentsException extends org.tealeaf.javamarkdown.exceptions.IllegalContentsException {

    /**
     * <p>Creates a new IllegalContents exception with the illegal class in question</p>
     *
     * @param illegalClass The illegal class passed as contents
     *
     * @since 0.0.8
     * @deprecated Use {@link org.tealeaf.javamarkdown.exceptions.IllegalContentsException#IllegalContentsException(Class)} instead
     */
    @Deprecated
    public IllegalContentsException(Class<?> illegalClass) {
        super(illegalClass);
    }
}
