package org.tealeaf.javamarkdown;

/**
 * @author Thomas Kwashnak
 * @since 0.0.8
 */
public class IllegalContentsException extends RuntimeException {

    private final Class<? extends MarkdownElement> invalidClass;

    public IllegalContentsException(Class<? extends MarkdownElement> invalidClass) {
        this.invalidClass = invalidClass;
    }

    @Override
    public String getMessage() {
        return String.format("Illegal Class class passed into object: %s", invalidClass.toString());
    }
}
