package org.tealeaf.javamarkdown.exceptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import test.Tests;

public class IllegalAlignmentCountExceptionTest {
    @Test
    void testGetMessage() {
        int a = Tests.randomInteger(), b = Tests.randomInteger();
        RuntimeException exception = new IllegalAlignmentCountException(a,b);

        assertTrue(exception.toString().contains(Integer.toString(a)));
        assertTrue(exception.toString().contains(Integer.toString(b)));
    }
}
