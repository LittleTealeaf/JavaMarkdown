package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

public class Bold extends Markup {

    public Bold(Object object) throws IllegalContentsException {
        super(object, "**");
    }

    @Override
    protected void checkType(Object object) throws IllegalContentsException {
        super.checkType(object);
        if(object instanceof Bold) {
            throw new IllegalContentsException(Bold.class);
        }
    }
}