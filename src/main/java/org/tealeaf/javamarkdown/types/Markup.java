package org.tealeaf.javamarkdown.types;

/**
 * Represents markdown elements that indicates a simple markup syntax to the content text, such as {@code **words**} to indicate <b>bold</b> text.
 *
 * @author Thomas Kwashnak
 * @since 0.0.1
 */
public class Markup extends InlineElement {

    /**
     * The syntax used in the markup language
     */
    protected final String syntax;
    /**
     * Object to insert into the markup
     */
    protected final Object object;

    /**
     * Creates a new markup element with a content object and the wrapping syntax
     *
     * @param object Content object to display
     * @param syntax String syntax to put on either side of the content
     * @since 0.0.1
     */
    public Markup(Object object, String syntax) {
        this.object = checkType(object);
        this.syntax = syntax;
    }

    /**
     * {@inheritDoc}
     * @since 0.0.9
     *
     */
    @Override
    public String asString() {
        //Converts the object to a string
        String contents = object.toString();

        //Creates a char array with a length equals to the contents length, plus the syntax length on both sides
        char[] string = new char[contents.length() + syntax.length() * 2];

        //Manually sets the characters for the syntax on both sides of the word simultaneously using char string
        for(int i = 0; i < syntax.length(); i++) {
            //Prints the syntax string at both the start and the end
            string[i] = string[i + syntax.length() + contents.length()] = syntax.charAt(i);
        }

        //Sets the characters from the contents string
        for(int i = 0; i < contents.length(); i++) {
            string[syntax.length() + i] = contents.charAt(i);
        }

        //Converts to a string
        return new String(string);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code false}
     * @since 0.0.9
     */
    @Override
    public boolean requiresNewlineBefore() {
        return false;
    }

    // @Override
    // public Writer toWriter(Writer writer) throws IOException {
    //     if(object instanceof MarkdownElement) {
    //         return ((MarkdownElement) object).toWriter(writer.append(syntax)).append(syntax);
    //     } else {
    //         return writer.append(syntax).append(object.toString()).append(syntax);
    //     }
    // }

    /**
     * {@inheritDoc}
     *
     * @return {@code false}
     *
     * @since 0.0.7
     */
    @Override
    public boolean requiresNewlineAfter() {
        return false;
    }
}
