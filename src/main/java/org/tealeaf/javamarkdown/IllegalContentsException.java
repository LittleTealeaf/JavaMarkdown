package org.tealeaf.javamarkdown;

public class IllegalContentsException extends Exception {

    private final Class<? extends MarkdownComponent> invalidClass;

    public IllegalContentsException(Class<? extends MarkdownComponent> invalidClass) {
        this.invalidClass = invalidClass;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid class passed into object: %s", invalidClass.toString());
    }
}
