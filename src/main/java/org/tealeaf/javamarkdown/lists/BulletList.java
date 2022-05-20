package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

public class BulletList extends ListStructure {

    private static final String SYMBOL = "- ";

    public BulletList(Object... objects) {
        super(SYMBOL,objects);
    }
}
