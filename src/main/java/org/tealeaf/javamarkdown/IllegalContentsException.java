package org.tealeaf.javamarkdown;

/**
 * <p>
 * When thrown, indicates that an illegal element was attempted to be passed as content of another element. For example, if a
 * {@link org.tealeaf.javamarkdown.lists.NumberedList NumberedList} was passed into a {@link org.tealeaf.javamarkdown.markup.Italic Italic} element, then this error woudl
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

    @Override
    public String getMessage() {
        return String.format("Illegal Class class passed into object: %s", illegalClass.toString());
    }
}
