package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

public class Bold extends Markup {

    public Bold(Object object)  {
        super(object, "**");
    }

    @Override
    protected void checkType(Object object)  {
        super.checkType(object);
        if(object instanceof Bold) {
            throw new IllegalContentsException(Bold.class);
        }
    }
}
