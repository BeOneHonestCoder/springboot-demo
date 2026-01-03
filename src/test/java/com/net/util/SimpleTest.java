package com.net.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    void testIsPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }

    boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }


    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, 20, 30"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }


    @ParameterizedTest
    @MethodSource("provideStringsForTest")
    void testWithMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> provideStringsForTest() {
        return Stream.of("apple", "banana");
    }
}
