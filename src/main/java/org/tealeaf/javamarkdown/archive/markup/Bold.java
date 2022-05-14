package org.tealeaf.javamarkdown.archive.markup;

import org.tealeaf.javamarkdown.IllegalContentsException;

@Deprecated
public class Bold extends Markup {

    public Bold(Object object) throws IllegalContentsException {
        super(object, "**");
    }
}
