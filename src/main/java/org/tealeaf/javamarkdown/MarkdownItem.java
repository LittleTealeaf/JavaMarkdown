package org.tealeaf.javamarkdown;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import java.util.Set;

public abstract class MarkdownItem {

    public MarkdownItem() {

    }

    public abstract Writer toWriter(Writer writer) throws IOException;

    public abstract String asString();

    @Override
    public String toString() {
        return asString();
    }

    protected abstract void checkType(Object object) throws IllegalContentsException;

    @Deprecated
    protected void testIllegalTypes(Object object, Set<Class<? extends MarkdownItem>> classStream) throws IllegalContentsException {
        Optional<Class<? extends MarkdownItem>> illegalType = classStream.parallelStream().filter(item -> item.isInstance(object)).findFirst();
        if (illegalType.isPresent()) {
            throw new IllegalContentsException(illegalType.get());
        }
    }

}
