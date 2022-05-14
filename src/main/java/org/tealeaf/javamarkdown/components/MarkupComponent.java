package org.tealeaf.javamarkdown.components;

public abstract class MarkupComponent extends Component {

    private final String markup;
    private final Object object;

    public MarkupComponent(Object object, String markup) {
        this.object = object;
        this.markup = markup;
    }

    @Override
    public String build() {
        return String.format("%s%s%s", markup,object,markup);
    }
}
