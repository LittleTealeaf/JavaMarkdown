package org.tealeaf.javamarkdown.archive.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

@Deprecated
public class Italics extends Markup {

    public Italics(Object object) throws IllegalContentsException {
        super(object, "*");
    }
}
