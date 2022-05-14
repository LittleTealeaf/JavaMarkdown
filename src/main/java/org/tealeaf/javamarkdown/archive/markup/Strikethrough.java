package org.tealeaf.javamarkdown.archive.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

@Deprecated
public class Strikethrough extends Markup {

    public Strikethrough(Object object) throws IllegalContentsException {
        super(object, "~~");
    }
}
