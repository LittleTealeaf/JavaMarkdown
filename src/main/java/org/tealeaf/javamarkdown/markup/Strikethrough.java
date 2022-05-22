package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.types.Markup;
import org.tealeaf.javamarkdown.types.Structure;

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
