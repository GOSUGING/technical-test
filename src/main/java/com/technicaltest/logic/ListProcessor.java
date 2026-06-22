package com.technicaltest.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Solución para la Parte 1: Lógica de Programación
 * 
 * Elimina duplicados y ordena una lista sin usar funciones nativas
 */
public class ListProcessor {

    /**
     * Procesa una lista: elimina duplicados y ordena de menor a mayor
     * Sin usar sort, sorted, set, distinct, unique o librerías equivalentes
     * 
     * @param input lista de números enteros
     * @return lista sin duplicados, ordenada ascendentemente
     */
    public static List<Integer> removeDuplicatesAndSort(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }

        // Paso 1: Eliminar duplicados manualmente
        List<Integer> noDuplicates = new ArrayList<>();
        for (int num : input) {
            if (!noDuplicates.contains(num)) {
                noDuplicates.add(num);
            }
        }

        // Paso 2: Ordenar usando Bubble Sort (implementación manual)
        bubbleSort(noDuplicates);

        return noDuplicates;
    }

    /**
     * Implementación manual de Bubble Sort
     * Ordena la lista de menor a mayor sin usar funciones nativas
     * 
     * @param list lista a ordenar
     */
    private static void bubbleSort(List<Integer> list) {
        int n = list.size();

        // Iteraciones externas (pases)
        for (int i = 0; i < n - 1; i++) {
            // Iteraciones internas (comparaciones y cambios)
            for (int j = 0; j < n - i - 1; j++) {
                // Comparar elementos adyacentes
                if (list.get(j) > list.get(j + 1)) {
                    // Intercambiar si están en orden incorrecto
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
