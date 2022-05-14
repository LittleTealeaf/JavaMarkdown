package org.tealeaf.javamarkdown.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

public class Bold extends Markup {

    public Bold(Object object) throws IllegalContentsException {
        super(object, "**");
    }
}
