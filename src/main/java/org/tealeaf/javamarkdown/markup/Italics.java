package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

public class Italics extends Markup {

    public Italics(Object object) throws IllegalContentsException {
        super(object, "*");
    }
}
