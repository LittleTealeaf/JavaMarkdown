package org.tealeaf.javamarkdown;

import org.tealeaf.javamarkdown.elements.Bold;
import org.tealeaf.javamarkdown.elements.NumberedList;
import org.tealeaf.javamarkdown.exceptions.IllegalContentsException;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a markdown item, or component, that contains its own method of printing itself.
 *
 * @author Thomas Kwashnak
 * @since 0.0.8
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
     * @since 0.0.8
     */
    public Writer toWriter(Writer writer) throws IOException {
        return writer.append(asString());
    }

    /**
     * Formats the element to a string
     *
     * @return String representation of the formatted item
     *
     * @since 0.0.8
     */
    public abstract String asString();

    @Override
    public String toString() {
        return asString();
    }

    /**
     * <p>Whether the element requires at least one {@code \n} before printing itself in a document</p>
     * <p>In instances such as in-line elements (like {@link Bold markup.Bold}), this would return {@code false} because bolds can be inserted
     * directly in-line. However, in instances such as {@link NumberedList lists.NumberedList}, it would be required that there is a new line
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
     * <p>This method is natively used in {@link MarkdownWriter#appendMarkdownElement(MarkdownElement)} to handle adding newlines when necessary</p>
     *
     * @return {@code true} if element requires a new line beforehand, {@code false} otherwise
     *
     * @see MarkdownWriter#appendMarkdownElement(MarkdownElement)
     * @since 0.0.8
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
     * <p>This method is natively used in {@link MarkdownWriter#appendMarkdownElement(MarkdownElement)} to handle adding newlines when necessary</p>
     *
     * @return {@code true} if element requires a new line after, {@code false} otherwise
     *
     * @see MarkdownWriter#MarkdownWriter()
     * @since 0.0.8
     */
    public abstract boolean requiresNewlineAfter();

    /**
     * <p>Checks the type of a passed object. This method is specific to each element and throws an {@link IllegalContentsException} if the passed object cannot be put
     * in as a content of that element. This prevents instances such as nested tables, where a table is put inside another table. If such feature is necessary, using the
     * {@link #toString()} of any markdown element will force it to be accepted in any spot</p>
     *
     * @param item The item to check it's class
     * @param <T>  The type of the item
     *
     * @return That item if it is not an instance of any illegal types
     *
     * @throws IllegalContentsException if the object passed is an illegal type for the element
     * @since 0.0.11
     */
    protected abstract <T> T checkType(T item) throws IllegalContentsException;

    /**
     * <p>Compares an object to a set of illegal MarkdownElement classes. If the object is an instance of any element, it will throw an {@link IllegalContentsException}</p>
     *
     * @param object      Object to test
     * @param classStream Set of illegal classes
     *
     * @since 0.0.8
     * @deprecated See {@link #checkType(Object)} for new method of checking
     */
    @Deprecated
    protected void testIllegalTypes(Object object, Set<Class<? extends MarkdownElement>> classStream) {
        Optional<Class<? extends MarkdownElement>> illegalType = classStream.parallelStream().filter(item -> item.isInstance(object)).findFirst();
        if (illegalType.isPresent()) {
            throw new IllegalContentsException(illegalType.get());
        }
    }
}
