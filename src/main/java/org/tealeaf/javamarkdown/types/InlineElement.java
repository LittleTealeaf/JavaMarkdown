package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

/**
 * A base type for any element that can be inserted within a sentence, such as simple markups or similar items.
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public abstract class InlineElement extends MarkdownElement {

    /**
     * {@inheritDoc}
     *
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the {@link Structure} class</pre>
     *
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the child class of {@link InlineElement}</pre>
     *
     * @since 0.0.11
     */
    @Override
    protected <T> T checkType(T object) {
        if (object instanceof Structure || getClass().isInstance(object)) {
            throw new IllegalContentsException(object.getClass());
        } else {
            return object;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return false
     * @since 0.0.19
     */
    @Override
    public boolean requiresNewlineAfter() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return false
     * @since 0.0.19
     */
    @Override
    public boolean requiresNewlineBefore() {
        return false;
    }

}
