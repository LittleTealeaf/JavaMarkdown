package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;

/**
 * <p>A base class for any {@link MarkdownElement} that formats its contents into a structure-syntax, such as tables or lists. This usually indicates that this Markdown
 * Element can't inserted within another Markdown Element</p>
 * @author Thomas Kwashnak
 * @since 0.0.7
 */
public abstract class Structure extends MarkdownElement {

    /**
     * {@inheritDoc}
     * <p>Requires that the object is not an instance of the {@link Structure} class</p>
     * @param object Object to check the data type of
     * @throws IllegalContentsException if {@code object} is an instance of the {@link Structure} class
     * @since 0.0.7
     */
    @Override
    protected void checkType(Object object) throws IllegalContentsException {
        if(object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        }
    }
}
