package com.technicaltest.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests para la Parte 1: Lógica de Programación
 */
public class ListProcessorTest {

    @Test
    public void testBasicExample() {
        // Ejemplo del enunciado: [4, 2, 7, 2, 4, 9, 1] -> [1, 2, 4, 7, 9]
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 2, 7, 2, 4, 9, 1));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 4, 7, 9));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullList() {
        List<Integer> result = ListProcessor.removeDuplicatesAndSort(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5));
        List<Integer> expected = new ArrayList<>(Arrays.asList(5));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testAllDuplicates() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 3, 3, 3));
        List<Integer> expected = new ArrayList<>(Arrays.asList(3));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testNegativeNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-5, -2, -5, 0, 3));
        List<Integer> expected = new ArrayList<>(Arrays.asList(-5, -2, 0, 3));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testAlreadySorted() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testReverseSorted() {
        List<Integer> input = new ArrayList<>(Arrays.asList(9, 7, 5, 3, 1));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }

    @Test
    public void testLargeNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1000000, 500000, 1000000, 100));
        List<Integer> expected = new ArrayList<>(Arrays.asList(100, 500000, 1000000));

        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);

        assertEquals(expected, result);
    }
}
