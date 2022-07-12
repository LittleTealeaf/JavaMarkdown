package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.Markup;

//Putting 0.0.19 just because that's when this was added
/**
 *
 * <p>Creates an element whose contents are crossed out, <del>such as this</del>.</p>
 * <p>Achieves this formatting by putting <code>~~</code> on either side of the content</p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.10
 *
 */
public class Strikethrough extends Markup {

    /**
     * <p>Creates a new StrikeThrough node, which renders its contents as <del>struck out</del>.</p>
     * <p>Does not allow for any {@link org.tealeaf.javamarkdown.types.Structure Structure} elements to be struck out.</p>
     *
     * @param content The content to be struck out in rendering
     * @since 0.0.10;
     */
    public Strikethrough(Object content) {
        super(content, "~~");
    }
}
