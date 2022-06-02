package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;

public abstract class InlineElement extends MarkdownElement {

    /**
     * {@inheritDoc}
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the {@link Structure} class</pre>
     */
    @Override
    protected <T> T checkType(T object) {
        if (object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        } else {
            return object;
        }
    }
}
