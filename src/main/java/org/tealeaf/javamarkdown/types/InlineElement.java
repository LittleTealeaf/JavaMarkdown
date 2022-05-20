package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;

public abstract class InlineElement extends MarkdownElement {

    @Override
    protected void checkType(Object object)  {
        if(object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        }
    }
}
