package org.tealeaf.javamarkdown.components;

import test.Randoms;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class SimpleMarkupTest {

    public void testMarkup(String markup) {
        String word = Randoms.randomWord();
        SimpleMarkup simpleMarkup = generate(word);
        assertEquals(markup + word + markup,simpleMarkup.build());
    }

    public abstract SimpleMarkup generate(Object object);
}