package org.tealeaf.javamarkdown.components;

@Deprecated
public abstract class Component {


    abstract public String build();

    @Override
    public String toString() {
        return build();
    }
}
