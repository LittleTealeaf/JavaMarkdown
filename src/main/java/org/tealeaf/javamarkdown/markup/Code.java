package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.MarkdownElement;
import org.tealeaf.javamarkdown.types.Markup;

public class Code extends Markup {

    public Code(Object object) {
        super(object, "`");
    }

    @Override
    protected void checkType(Object object) {
        super.checkType(object);
        if(object instanceof MarkdownElement) {
            throw new IllegalContentsException(MarkdownElement.class);
        }
    }
}
