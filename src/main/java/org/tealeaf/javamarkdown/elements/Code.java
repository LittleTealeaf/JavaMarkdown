package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

/**
 * <p>Prints out inline-text as a small code snippet, such as <code>This</code>.</p>
 * <p>Note that in markdown, code sections have a colored background (such as dark grey)</p>
 * @author Thomas Kwashnak
 * @since 0.0.1
 */
public class Code extends Markup {

    /**
     * <p>Creates a code snippet with the provided content</p>
     * @param content Content to include in the code block
     */
    public Code(Object content) {
        super(content, "`");
    }

    /**
     * {@inheritDoc}
     * <pre>Throws {@link IllegalContentsException} if the object is an instance of {@link MarkdownElement}</pre>
     */
    @Override
    protected <T> T checkType(T object) {
        if (object instanceof MarkdownElement) {
            throw new IllegalContentsException(object.getClass());
        } else {
            return super.checkType(object);
        }
    }
}
