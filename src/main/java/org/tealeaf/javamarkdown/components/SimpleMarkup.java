package org.tealeaf.javamarkdown.components;

@Deprecated
public abstract class SimpleMarkup extends Component {

    private final String markup;
    private final Object object;

    public SimpleMarkup(Object object, String markup) {
        this.object = object;
        this.markup = markup;
    }

    @Override
    public String build() {
        return String.format("%s%s%s", markup,object,markup);
    }
}
