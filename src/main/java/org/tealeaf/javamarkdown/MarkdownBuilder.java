package org.tealeaf.javamarkdown;

/**
 * <p>
 * A builder, or writer, that implements methods for appending markdown elements, as well as the ability to directly append markdown elements
 * </p>
 * @author Thomas Kwashnak
 * @since 0.0.7
 * @deprecated Refactored to {@link MarkdownWriter}. This class now extends {@code MarkdownWriter} to keep its functionality, but will inevitably be removed in favor for
 * {@code MarkdownWriter}
 */
@Deprecated
public class MarkdownBuilder extends MarkdownWriter {}
