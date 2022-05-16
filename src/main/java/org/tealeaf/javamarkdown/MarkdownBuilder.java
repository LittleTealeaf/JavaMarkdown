package org.tealeaf.javamarkdown;

public class MarkdownBuilder {

    private boolean addSpaceBeforeInserting = true;


    public MarkdownBuilder() {

    }

    public MarkdownBuilder add(Object object) {
        return this;
    }

    public MarkdownBuilder addBold(Object object) {
        return this;
    }

    public MarkdownBuilder addItalic(Object object) {
        return this;
    }

    public MarkdownBuilder addStrikethrough(Object object) {
        return this;
    }

    public MarkdownBuilder addLink(String url, Object object) {
        return this;
    }

    public MarkdownBuilder addImage(String source) {
        return this;
    }

    public void setAddSpaceBeforeInserting(boolean addSpaceBeforeInserting) {
        this.addSpaceBeforeInserting = addSpaceBeforeInserting;
    }
}
