package org.tealeaf.javamarkdown;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public abstract class MarkdownComponent {

    public MarkdownComponent() {

    }

    public abstract Writer toWriter(Writer writer) throws IOException;

    public abstract String asString();

    @Override
    public String toString() {
        return asString();
    }

    protected void testIllegalTypes(Object object, Set<Class<? extends MarkdownComponent>> classStream) throws IllegalContentsException {
        Optional<Class<? extends MarkdownComponent>> illegalType = classStream.parallelStream().filter(item -> item.isInstance(object)).findFirst();
        if (illegalType.isPresent()) {
            throw new IllegalContentsException(illegalType.get());
        }
    }
}
