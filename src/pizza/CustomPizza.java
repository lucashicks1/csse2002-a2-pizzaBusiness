package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;
import pizza.ingredients.Topping;

import java.util.List;

/**
 * Represents a custom pizza that allows the addition of extra toppings for a more delicious pizza.
 */
public class CustomPizza extends Pizza implements MenuItem {

    /**
     * Creates a custom pizza that is a medium cheese pizza with no toppings
     */
    public CustomPizza() {
        super();
        setName("Custom Pizza");
    }

    /**
     * Creates a pizza with a given size, sauce and cheese with no toppings
     * @param size size of the pizza
     * @param sauce sauce on the pizza
     * @param cheese cheese on the pizza
     */
    public CustomPizza(BaseSize size, Sauce sauce, Cheese cheese) throws IllegalArgumentException {
        super(size, sauce, cheese);
        setName("Custom Pizza");
    }

    /**
     * Adds a list of toppings to a pizza, only if the maximum number of toppings is not exceeded
     * @param toppings list of toppings to be added to the pizza
     * @throws TooManyToppingsException if the new toppings cause the toppings total to be > 5
     */
    public void add(List<Topping> toppings)
            throws TooManyToppingsException, IllegalArgumentException {
        if (toppings == null) {
            throw new IllegalArgumentException("Toppings is null");
        } else if ((toppings.size() + accessToppings().size()) > MAX_TOPPINGS) {
            throw new TooManyToppingsException("Too many toppings to add");
        } else {
            for (Topping topping : toppings) {
                add(topping);
            }
        }
    }

    /**
     * Adds a single topping to the pizza,
     * @param topping topping to be added pizza
     */
    public void add(Topping topping) throws TooManyToppingsException, IllegalArgumentException {
        if (topping == null) {
            throw new IllegalArgumentException("Topping is null");
        } else if (accessToppings().size() > 4) {
            throw new TooManyToppingsException("Adding topping exceeds 5 total toppings");
        }
        accessToppings().add(topping);
    }

    /**
     * Removes the first occurrence of a specified topping
     * @param topping topping to be removed from the pizza
     */
    public void remove(Topping topping) {
        accessToppings().remove(topping);
    }
}