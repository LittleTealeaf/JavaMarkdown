package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

public abstract class InlineElement extends MarkdownElement {

    /**
     * {@inheritDoc}
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the {@link Structure} class</pre>
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the child class of {@link InlineElement}</pre>
     */
    @Override
    protected <T> T checkType(T object) {
        if (object instanceof Structure || getClass().isInstance(object)) {
            throw new IllegalContentsException(object.getClass());
        } else {

            return object;
        }
    }
}
