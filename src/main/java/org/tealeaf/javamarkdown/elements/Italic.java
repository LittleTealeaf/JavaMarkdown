package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Markup;

/**
 * <p>Renders some content in <i>Italics</i></p>
 * @author Thomas Kwashnak
 * @since 0.0.10
 */
public class Italic extends Markup {

    /**
     * <p>Creates an {@code Italics} object, and sets the content to be rendered in <i>italics</i></p>
     * @since 0.0.10
     * @param content Object content to render
     */
    public Italic(Object content) {
        super(content, "*");
    }
}
