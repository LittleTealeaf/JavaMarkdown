package org.tealeaf.javamarkdown.components;

import java.util.Arrays;
import java.util.function.BinaryOperator;

@Deprecated
public class BlockQuote extends Component {

    private final Object object;

    public BlockQuote(Object object) {
        this.object = object;
    }

    @Override
    public String build() {
        return String.format("> %s", String.join("\n> ",object.toString().split("\n")));
    }
}
