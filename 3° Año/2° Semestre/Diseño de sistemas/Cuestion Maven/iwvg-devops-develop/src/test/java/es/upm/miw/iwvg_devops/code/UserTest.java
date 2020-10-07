package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;
    private List<Fraction> fractions = List.of(new Fraction(1, 2), new Fraction(1, 4));

    @BeforeEach
    void before() {
        this.user = new User("1", "Lucas", "Soria", this.fractions);
    }

    @Test
    void testGetId() {
        assertEquals("1", this.user.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Lucas", this.user.getName());
    }

    @Test
    void testGetFamilyName() {
        assertEquals("Soria", this.user.getFamilyName());
    }

    @Test
    void testFractions() {
        assertEquals(this.fractions, this.user.getFractions());
    }

    @Test
    void testFullName() {
        assertEquals("Lucas Soria", this.user.fullName());
    }

    @Test
    void testInitials() {
        assertEquals("L.", this.user.initials());
    }

    @Test
    void testToString() {
        String string = "User{" +
                "id='" + "1" + '\'' +
                ", name='" + "Lucas" + '\'' +
                ", familyName='" + "Soria" + '\'' +
                ", fractions=" + this.fractions +
                '}';
        assertEquals(string, this.user.toString());
    }

    @Test
    void testSetName() {
        User user = new User();
        user.setName("Lucas");
        assertEquals("Lucas", user.getName());
    }

    @Test
    void testSetFamilyName() {
        User user = new User();
        user.setFamilyName("Soria");
        assertEquals("Soria", user.getFamilyName());
    }

    @Test
    void testSetFractions() {
        User user = new User();
        List<Fraction> fractions = List.of(new Fraction(2, 4), new Fraction(5, 8));
        user.setFractions(fractions);
        assertEquals(fractions, user.getFractions());
    }

    @Test
    void testAddFractions() {
        User user = new User();
        Fraction fraction = new Fraction(2, 4);
        user.addFraction(fraction);
        assertEquals(List.of(fraction), user.getFractions());
    }

}
