package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

public class Italic extends Markup {

    public Italic(Object object)  {
        super(object, "*");
    }

    @Override
    protected void checkType(Object object)  {
        super.checkType(object);
        if(object instanceof Italic) {
            throw new IllegalContentsException(Italic.class);
        }
    }
}
