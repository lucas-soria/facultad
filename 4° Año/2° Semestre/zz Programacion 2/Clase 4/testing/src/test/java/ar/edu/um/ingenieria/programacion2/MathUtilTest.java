package ar.edu.um.ingenieria.programacion2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

public class MathUtilTest {

    @BeforeAll
    static void antes_de_todo() {
        System.out.println("\n\nEsto ocurre una sola vez\n\n");
    }

    @BeforeEach
    void antes_de_cada_test() {
        System.out.println("\nAntes de cada test\n");
    }

    @Test
    @DisplayName("Esto es lo que muestra test_Add")
    void test_Add() {
        assertEquals(5, MathUtil.add(3, 2));
    }

    @Test
    void test_Multiply() {
        assertEquals(15, MathUtil.multiple(3, 5));
    }

    @Test
    void test_Divide() {
        assertEquals(5, MathUtil.divide(25, 5));
    }

    @Test
    void testIs_Prime() {
        assertTrue(MathUtil.isPrime(13));
    }

    @Test
    void test_Not_Is_Prime() {
        assertFalse(MathUtil.isPrime(12));
    }

    @Test
    void test_Is_Even() {
        assertTrue(MathUtil.isEven(2));
    }

    @Test
    @Disabled
    void test_Not_Is_Even() {
        assertFalse(MathUtil.isEven(13));
    }

    @AfterEach
    void me_ejecuto_despues_de_cada_test() {
        System.out.println("Esto pasa después de cada test");
    }

    @AfterAll
    static void me_ejecuto_al_final() {
        System.out.println("\n\nAdios\n\n");
    }

}
