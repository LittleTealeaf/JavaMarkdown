package org.tealeaf.javamarkdown.components;

public abstract class Component {


    abstract public String build();

    @Override
    public String toString() {
        return build();
    }
}
