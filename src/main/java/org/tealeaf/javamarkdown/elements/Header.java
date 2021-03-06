package org.tealeaf.javamarkdown.elements;

import org.tealeaf.javamarkdown.exceptions.IllegalHeaderLevelException;
import org.tealeaf.javamarkdown.types.Structure;

/**
 * <p>Represents a header in a markdown document. This will render the content in a large font, depending on the header size</p>
 *
 * @author Thomas Kwashnak
 * @since 0.0.12
 */
public class Header extends Structure {

    private final int level;
    private final Object content;

    /**
     * Creates a new level-1 header
     *
     * @param content Content to display in the header
     *
     * @since 0.0.12
     */
    public Header(Object content) {
        this.level = 1;
        this.content = checkType(content);
    }

    /**
     * <p>Creates a new header of a specified level.</p>
     *
     * @param level   The header level to display. Can only be a number between 1-6
     * @param content The content to display in the header
     *
     * @throws IllegalHeaderLevelException if a level not within 1-6 is passed
     * @since 0.0.12
     */
    public Header(int level, Object content) {
        if (level < 1 || level > 6) {
            throw new IllegalHeaderLevelException(level);
        }
        this.level = level;
        this.content = checkType(content);
    }

    /**
     * {@inheritDoc}
     * @since 0.0.12
     */
    @Override
    public String asString() {
        //Converts content to a string
        String contentString = content.toString();

        //Gets the contents length
        int contentLength = contentString.length();

        //Creates a string equal to 1 (the space) + level (the number of #) + contentLength (the length of content)
        char[] str = new char[1 + level + contentLength];

        //Sets the # characters in each of the first n chars
        for(int i = 0; i < level; i++) {
            str[i] = '#';
        }

        //Sets space to the next char
        str[level] = ' ';

        //Fills in the content characters
        for(int i = 0; i < contentLength; i++) {
            str[level + i + 1] = contentString.charAt(i);
        }

        //Converts to a String
        return new String(str);
    }
}
