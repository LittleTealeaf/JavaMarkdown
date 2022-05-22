package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;

public abstract class InlineElement extends MarkdownElement {

    @Override
    protected <T> T checkType(T object)  {
        if(object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        } else {
            return object;
        }
    }
}
