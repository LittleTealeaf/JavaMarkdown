package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

/**
 * <p>A base class for any {@link MarkdownElement} that formats its contents into a structure-syntax, such as tables or lists. This usually indicates that this Markdown
 * Element can't inserted within another Markdown Element</p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.7
 */
public abstract class Structure extends MarkdownElement {

    /**
     * {@inheritDoc}
     * <pre>Throws {@link IllegalContentsException} if object is an instance of the {@link Structure} class</pre>
     *
     * @since 0.0.7
     */
    @Override
    protected <T> T checkType(T object) throws IllegalContentsException {
        if (object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        } else {
            return object;
        }
    }
}
