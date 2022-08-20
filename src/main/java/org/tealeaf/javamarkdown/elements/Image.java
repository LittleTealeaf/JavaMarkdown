package org.tealeaf.javamarkdown.elements;

/**
 * Displays an image within the document. Allows specifying the alt text.
 * @since 0.0.12
 * @author Thomas Kwashnak
 */
public class Image extends Link {

    /**
     * Implements an image with no alt text.
     * @param src Image source url
     *
     * @since 0.0.12
     */
    public Image(String src) {
        super("", src);
    }

    /**
     *
     * @param alt Alternate text used for the image
     * @param src Image source url
     * @since 0.0.12
     */
    public Image(String alt, String src) {
        super(alt, src);
    }

    /**
     * {@inheritDoc}
     * @since 0.0.12
     */
    @Override
    public String asString() {
        return String.format("!%s", super.asString());
    }
}
