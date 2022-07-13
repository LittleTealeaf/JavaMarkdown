package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.types.InlineElement;

/**
 * Creates a clickable link in the Markdown Document. Allows setting both the
 * link and the display content
 *
 * @author Thomas Kwashnak
 * @since 0.0.12
 *
 */
public class Link extends InlineElement {

    private final Object content;
    private final String url;

    /**
     * Creates a simple link that displays the same url that it links to
     *
     * @param url Link url
     * @since 0.0.12
     */
    public Link(String url) {
        this(url, url);
    }

    /**
     * Creates a link with the specified display and the url it links to
     * @param content What you want to be displayed for the link
     * @param url The url that the link should link to
     * @since 0.0.12
     */
    public Link(Object content, String url) {
        this.content = checkType(content);
        this.url = url;
    }

    /**
     * Formats the string into the link syntax used in Markdown
     * @since 0.0.12
     */
    @Override
    public String asString() {
        return String.format("[%s](%s)", content, url);
    }

    /**
     * {@inheritDoc}}
     * @return false
     */
    @Override
    public boolean requiresNewlineBefore() {
        return false;
    }

    /**
     * {@inheritDoc}
     * @return false
     */
    @Override
    public boolean requiresNewlineAfter() {
        return false;
    }
}
