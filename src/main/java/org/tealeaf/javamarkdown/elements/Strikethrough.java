package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Markup;

public class Strikethrough extends Markup {

    public Strikethrough(Object object) {
        super(object, "~~");
    }
}
