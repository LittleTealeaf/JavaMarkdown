package org.tealeaf.javamarkdown.components;

import org.tealeaf.javamarkdown.MarkdownComponent;

public class Bold implements MarkdownComponent {

    private final Object object;

    public Bold(Object object) {
        this.object = object;
    }

    public StringBuilder appendOn(StringBuilder stringBuilder) {
        return stringBuilder.append("**").append(object.toString()).append("**");
    }
}
