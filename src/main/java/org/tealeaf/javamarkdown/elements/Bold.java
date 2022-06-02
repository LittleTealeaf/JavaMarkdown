package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

/**
 * Renders a section of text in markdown as bold.
 * @author Thomas Kwashnak
 * @since 0.0.1
 */
public class Bold extends Markup {

    private static final String MARKUP = "**";

    /**
     * <p>Creates a new bold section of text.</p>
     * <p>Content within this object will be rendered <b>as bold</b></p>
     * @param content
     */
    public Bold(Object content)  {
        super(content, MARKUP);
    }

    /**
     * {@inheritDoc}
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the {@link Bold} class</pre>
     */
    @Override
    protected <T> T checkType(T object) throws IllegalContentsException {
        if(object instanceof Bold) {
            throw new IllegalContentsException(Bold.class);
        } else {
            return super.checkType(object);
        }
    }
}
