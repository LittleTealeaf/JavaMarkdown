package org.tealeaf.javamarkdown.lists;

import org.tealeaf.javamarkdown.types.ListStructure;

public class BulletList extends ListStructure {

    private static final String PREFIX = "- ";

    public BulletList(Object... objects) {
        super(objects);
    }

    @Override
    protected String getPrefix(int index) {
        return PREFIX;
    }
}
