package org.tealeaf.javamarkdown.types;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownItem;

public abstract class InlineItem extends MarkdownItem {

    @Override
    protected void checkType(Object object) throws IllegalContentsException {
        if(object instanceof Structure) {
            throw new IllegalContentsException(Structure.class);
        }
    }
}
