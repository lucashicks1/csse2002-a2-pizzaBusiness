package pizza.ingredients;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToppingTest {

    @Before
    public void setUp() {
        Topping.resetToppings();
    }

    @Test
    public void testCreateToppingVegan(){
        Topping.createTopping("Topping 1", true);
        Topping topping1 = Topping.valueOf("Topping 1");
        assertTrue(topping1.isVegan());
    }

    @Test
    public void testCreateToppingNotVegan() {
        Topping.createTopping("Topping 1", false);
        Topping topping1 = Topping.valueOf("Topping 1");
        assertFalse(topping1.isVegan());
    }

    @Test
    public void testCreateToppingName() {
        Topping.createTopping("Topping 1", false);
        Topping topping1 = Topping.valueOf("Topping 1");
        assertEquals("TOPPING 1", topping1.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateToppingNull() {
        Topping.createTopping(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateToppingDuplicateExact() {
        Topping.createTopping("Topping 1", true);
        Topping.createTopping("Topping 1", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateToppingDuplicate() {
        Topping.createTopping("Topping 1", true);
        Topping.createTopping("TOPPING 1", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateToppingBlank() {
        Topping.createTopping("", false);
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testCreateToppingSpaces() {
        Topping.createTopping(" ", false);
    }

    @Test
    public void testValueOfExact() {
        Topping.createTopping("Topping 1", false);
        Topping testTopping = Topping.valueOf("Topping 1");
        assertEquals("TOPPING 1", testTopping.toString());
    }

    @Test
    public void testValueOfSimilar() {
        Topping.createTopping("Topping 1", false);
        Topping testTopping = Topping.valueOf("TOPPING 1");
        assertEquals("TOPPING 1", testTopping.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testValueOfNoName() {
        Topping.valueOf("No Name");
    }

    @Test (expected = NullPointerException.class)
    public void testValueOfNull() {
        Topping.valueOf(null);
    }

    @Test
    public void testValues() {
        Topping.createTopping("T1", true);
        Topping.createTopping("T2", true);
        Topping[] toppings = {Topping.valueOf("T1"), Topping.valueOf("T2")};

        assertArrayEquals(toppings, Topping.values());
    }

    @Test
    public void testResetToppings() {
        Topping.createTopping("Test Topping", false);
        Topping.resetToppings();

        Topping[] toppings = new Topping[0];
        assertArrayEquals(toppings, Topping.values());
    }

    @Test
    public void testToString() {
        Topping.createTopping("Test Topping", false);
        Topping topping1 = Topping.valueOf("Test Topping");
        assertEquals("TEST TOPPING", topping1.toString());
    }

    @Test
    public void testIsVegan() {
        Topping.createTopping("Test Topping", true);
        Topping topping1 = Topping.valueOf("Test Topping");
        assertTrue(topping1.isVegan());
    }

}