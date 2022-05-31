package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

public class Bold extends Markup {

    public Bold(Object object)  {
        super(object, "**");
    }

    @Override
    protected <T> T checkType(T object)  {
        if(object instanceof Bold) {
            throw new IllegalContentsException(Bold.class);
        } else {
            return super.checkType(object);
        }
    }
}
