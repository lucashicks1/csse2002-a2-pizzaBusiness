package pizza;
import exceptions.TooManyToppingsException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomPizzaTest {

    private CustomPizza pizza1;

    private static Topping topping1;
    private static Topping topping2;
    private static Topping topping3;

    @BeforeClass
    public static void createToppings() {
        Topping.createTopping("Topping 1", false);
        Topping.createTopping("Topping 2", false);
        Topping.createTopping("Topping 3", false);
        topping1 = Topping.valueOf("Topping 1");
        topping2 = Topping.valueOf("Topping 2");
        topping3 = Topping.valueOf("Topping 3");
    }

    @Before
    public void setUp() {
        pizza1 = new CustomPizza();
    }

    @Test
    public void testConstructorDefaultToppings() {
        List<Topping> toppings = new ArrayList<>();
        assertEquals(toppings, pizza1.getToppings());
    }

    @Test
    public void testConstructorName() {
        assertEquals("Custom Pizza", pizza1.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSize() {
        CustomPizza testPizza = new CustomPizza(null, Sauces.Sauce.BBQ, Cheeses.Cheese.VEGAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSauce() {
        CustomPizza testPizza = new CustomPizza(Bases.BaseSize.MEDIUM, null, Cheeses.Cheese.MOZZARELLA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorCheese() {
        CustomPizza testPizza = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.GARLIC, null);
    }

    @Test
    public void testAddList() throws TooManyToppingsException {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.valueOf("Topping 1"));

        pizza1.add(toppings);
        assertEquals(toppings, pizza1.accessToppings());
    }

    @Test(expected = TooManyToppingsException.class)
    public void testAddListTooMany() throws TooManyToppingsException {

        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(topping1);
        toppings.add(topping2);
        toppings.add(topping3);
        toppings.add(topping1);
        toppings.add(topping2);
        toppings.add(topping3);

        pizza1.add(toppings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddListNull() throws TooManyToppingsException {
        ArrayList<Topping> toppings = null;
        pizza1.add(toppings);
    }

    @Test
    public void testAddTopping() throws TooManyToppingsException {

        pizza1.add(topping1);
        pizza1.add(topping2);
        pizza1.add(topping3);
        pizza1.add(topping1);
        pizza1.add(topping2);

        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(topping1);
        toppings.add(topping2);
        toppings.add(topping3);
        toppings.add(topping1);
        toppings.add(topping2);

        assertEquals(toppings, pizza1.accessToppings());
    }

    @Test(expected = TooManyToppingsException.class)
    public void testAddToppingTooMany() throws TooManyToppingsException {

        pizza1.add(topping1);
        pizza1.add(topping2);
        pizza1.add(topping3);
        pizza1.add(topping1);
        pizza1.add(topping2);
        pizza1.add(topping3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToppingNull() throws TooManyToppingsException {
        Topping topping1 = null;
        pizza1.add(topping1);
    }

    @Test
    public void testRemoveNone() throws TooManyToppingsException {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(topping1);
        toppings.add(topping2);

        pizza1.add(toppings);
        pizza1.remove(topping3);

        assertEquals(toppings, pizza1.getToppings());
    }

    @Test
    public void testRemoveFirst() throws TooManyToppingsException {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(topping2);
        toppings.add(topping1);

        pizza1.add(topping1);
        pizza1.add(topping2);
        pizza1.add(topping1);

        pizza1.remove(topping1);

        assertEquals(toppings, pizza1.getToppings());
    }
}