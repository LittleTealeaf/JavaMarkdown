package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;

public class Strikethrough extends Markup {

    public Strikethrough(Object object)  {
        super(object, "~~");
    }

    @Override
    protected <T> T checkType(T object) {
        if(object instanceof Strikethrough) {
            throw new IllegalContentsException(Strikethrough.class);
        } else {
            return super.checkType(object);
        }
    }
}
