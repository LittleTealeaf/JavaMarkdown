package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

public class Strikethrough extends Markup {

    public Strikethrough(Object object) throws IllegalContentsException {
        super(object, "~~");
    }
}
