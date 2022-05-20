package org.tealeaf.javamarkdown;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a markdown item, or component, that contains its own method of printing itself.
 *
 * @author Thomas Kwashnak
 */
public abstract class MarkdownElement {

    /**
     * Writes the formatted item to a Writer
     *
     * @param writer Writer to write contents to
     *
     * @return The writer passed in the parameters
     *
     * @throws IOException If any exceptions were thrown during writing
     */
    public abstract Writer toWriter(Writer writer) throws IOException;

    /**
     * Formats the element to a string
     *
     * @return String representation of the formatted item
     */
    public abstract String asString();

    /**
     * @return The element as a string
     * @see #asString()
     */
    @Override
    public String toString() {
        return asString();
    }

    /**
     * <p>Whether the element requires at least one {@code \n} before printing itself in a document</p>
     * <p>In instances such as in-line elements (like {@link org.tealeaf.javamarkdown.markup.Bold markup.Bold}), this would return {@code false} because bolds can be inserted
     * directly in-line. However, in instances such as {@link org.tealeaf.javamarkdown.lists.NumberedList lists.NumberedList}, it would be required that there is a new line
     * to prevent
     * the
     * following
     * </p>
     * <pre>
     *     Some sentence - item 1
     *     - item 2
     *     - item 3
     * </pre>
     * <p>and instead print the following</p>
     * <pre>
     *     Some sentence
     *     - item 1
     *     - item 2
     * </pre>
     * <p>This method is natively used in {@link MarkdownBuilder#appendMarkdownElement(MarkdownElement)} to handle adding newlines when necessary</p>
     *
     * @return {@code true} if element requires a new line beforehand, {@code false} otherwise
     *
     * @see MarkdownBuilder#appendMarkdownElement(MarkdownElement)
     */
    public abstract boolean requiresNewlineBefore();

    /**
     * <p>Whether a new line should be added after inserting the element into a document. This is particularly useful for structures that need to have a newline after it
     * gets rendered</p>
     * <p>For example, if some text was printed immediately after a list, it would render as:</p>
     * <pre>
     *     - item 1
     *     - item 2
     *     - item 3some text
     * </pre>
     * <p>Thus, by requiring a new line after the element is rendered, we get the following output instead:</p>
     * <pre>
     *     - item 1
     *     - item 2
     *     - item 3
     *     some text
     * </pre>
     * <p>This method is natively used in {@link MarkdownBuilder#appendMarkdownElement(MarkdownElement)} to handle adding newlines when necessary</p>
     *
     * @return {@code true} if element requires a new line after, {@code false} otherwise
     *
     * @see MarkdownBuilder#MarkdownBuilder()
     */
    public abstract boolean requiresNewlineAfter();

    /**
     * <p>Checks the type of some passed object. This method is specific to the element and throws an {@link IllegalContentsException} if an illegal object type is passed
     * through. This prevents instances such as tables or other similar elements being directly inserted into elements such as a {@link org.tealeaf.javamarkdown.markup.Bold
     * Bold} or {@link org.tealeaf.javamarkdown.lists.NumberedList NumberedList} element.
     * </p>
     *
     * @param object Object to check the data type of
     *
     * @throws IllegalContentsException if the object passed is an illegal type for the element
     */
    protected abstract void checkType(Object object) throws IllegalContentsException;

    /**
     * <p>Compares an object to a set of illegal MarkdownElement classes. If the object is an instance of any element, it will throw an {@link IllegalContentsException}</p>
     * @deprecated See {@link #checkType(Object)} for new method of checking
     * @param object
     * @param classStream
     */
    @Deprecated
    protected void testIllegalTypes(Object object, Set<Class<? extends MarkdownElement>> classStream) {
        Optional<Class<? extends MarkdownElement>> illegalType = classStream.parallelStream().filter(item -> item.isInstance(object)).findFirst();
        if (illegalType.isPresent()) {
            throw new IllegalContentsException(illegalType.get());
        }
    }
}
