package org.tealeaf.javamarkdown.types;

import java.io.IOException;
import java.io.Writer;

/**
 * <p>Represents a named list of elements, like {@link ListStructure}</p>
 * @author Thomas Kwashnak
 * @since 0.0.11
 */
public abstract class NamedListStructure extends ListStructure {

    protected String name;

    /**
     * Creates a new Named List with a name and an initial set of objects
     * @param name Name of the list
     * @param objects Set of initial objects
     */
    public NamedListStructure(String name, Object... objects) {
        super(objects);
        this.name = name;
    }

    @Override
    public String asString() {
        return String.format("%s\n%s",name,super.asString());
    }

    @Override
    public Writer toWriter(Writer writer) throws IOException {
        return super.toWriter(writer.append(String.format("%s\n", name)));
    }
}
