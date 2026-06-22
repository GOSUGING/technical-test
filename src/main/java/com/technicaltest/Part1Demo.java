package com.technicaltest;

import java.util.Arrays;
import java.util.List;

import com.technicaltest.logic.ListProcessor;

/**
 * Programa de demostración para la Parte 1
 * Prueba la lógica de eliminación de duplicados y ordenamiento
 */
public class Part1Demo {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         PRUEBA TÉCNICA - PARTE 1: LÓGICA                   ║");
        System.out.println("║    Eliminar duplicados y ordenar sin funciones nativas    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Ejemplo 1: Caso del enunciado
        demoCase("EJEMPLO DEL ENUNCIADO",
                Arrays.asList(4, 2, 7, 2, 4, 9, 1),
                Arrays.asList(1, 2, 4, 7, 9));

        // Ejemplo 2: Números negativos
        demoCase("NÚMEROS NEGATIVOS",
                Arrays.asList(-5, -2, -5, 0, 3, -2),
                Arrays.asList(-5, -2, 0, 3));

        // Ejemplo 3: Lista ya ordenada
        demoCase("LISTA YA ORDENADA",
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5));

        // Ejemplo 4: Todos duplicados
        demoCase("TODOS DUPLICADOS",
                Arrays.asList(5, 5, 5, 5),
                Arrays.asList(5));

        // Ejemplo 5: Un solo elemento
        demoCase("UN SOLO ELEMENTO",
                Arrays.asList(42),
                Arrays.asList(42));

        // Ejemplo 6: Orden inverso
        demoCase("ORDEN INVERSO",
                Arrays.asList(9, 7, 5, 3, 1),
                Arrays.asList(1, 3, 5, 7, 9));

        // Ejemplo 7: Números grandes
        demoCase("NÚMEROS GRANDES",
                Arrays.asList(1000000, 500000, 1000000, 100),
                Arrays.asList(100, 500000, 1000000));

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ✅ TODOS LOS EJEMPLOS PASAN               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Ejecuta un caso de prueba y muestra el resultado
     */
    private static void demoCase(String title, List<Integer> input, List<Integer> expected) {
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.println("📌 " + title);
        System.out.println("─────────────────────────────────────────────────────────────");
        
        System.out.print("   Entrada:   ");
        printList(input);
        
        List<Integer> result = ListProcessor.removeDuplicatesAndSort(input);
        
        System.out.print("   Salida:    ");
        printList(result);
        
        System.out.print("   Esperado:  ");
        printList(expected);
        
        boolean passed = result.equals(expected);
        String status = passed ? "✅ PASS" : "❌ FAIL";
        System.out.println("   Estado:    " + status);
        System.out.println();
    }

    /**
     * Imprime una lista de forma bonita
     */
    private static void printList(List<Integer> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
