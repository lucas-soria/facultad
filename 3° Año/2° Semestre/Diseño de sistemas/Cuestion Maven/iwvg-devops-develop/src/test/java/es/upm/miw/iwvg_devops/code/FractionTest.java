package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FractionTest {

    private Fraction fraction;

    @BeforeEach
    void before() {
        this.fraction = new Fraction();
    }

    @Test
    void testGetNumerador() {
        assertEquals(1, this.fraction.getNumerator());
    }

    @Test
    void testGetDenominador() {
        assertEquals(1, this.fraction.getDenominator());
    }

    @Test
    void testSetNumerador() {
        this.fraction.setNumerator(5);
        assertEquals(5, this.fraction.getNumerator());
    }

    @Test
    void testSetDenominador() {
        this.fraction.setDenominator(9);
        assertEquals(9, this.fraction.getDenominator());
    }

    @Test
    void testDecimal() {
        Fraction fraction = new Fraction(4, 29);
        assertEquals(0.138, fraction.decimal(), 10e-3);
    }

}
