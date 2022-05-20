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
     *
     * @see MarkdownBuilder#appendMarkdownItem(MarkdownElement)
     * @return {@code true} if element requires a new line beforehand, {@code false} otherwise
     */
    public abstract boolean requiresNewlineBefore();

    /**
     * Whether to insert a {@code \n} after the formatted element
     *
     * @return {@code true} if there needs to be at least one {@code \n} after the formatted element, {@code false} if not.
     */
    public abstract boolean requiresNewlineAfter();
    protected abstract void checkType(Object object) throws IllegalContentsException;

    @Deprecated
    protected void testIllegalTypes(Object object, Set<Class<? extends MarkdownElement>> classStream) {
        Optional<Class<? extends MarkdownElement>> illegalType = classStream.parallelStream().filter(item -> item.isInstance(object)).findFirst();
        if (illegalType.isPresent()) {
            throw new IllegalContentsException(illegalType.get());
        }
    }
}
