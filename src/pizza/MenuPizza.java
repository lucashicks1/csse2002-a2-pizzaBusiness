package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;
import pizza.ingredients.Topping;

import java.util.List;

/**
 * Represents a pre-organised pizza that is available from the menu
 */
public class MenuPizza extends Pizza {

    /**
     * Creates a menu pizza with a given size, sauce, cheese and list of toppings
     * @param size size of the pizza
     * @param sauce sauce on the pizza
     * @param cheese cheese on the pizza
     * @param toppings list of toppings on the pizza
     * @throws TooManyToppingsException if there are more than 5 toppings provided
     */
    public MenuPizza(BaseSize size, Sauce sauce, Cheese cheese, List<Topping> toppings)
            throws TooManyToppingsException, IllegalArgumentException {
        super(size, sauce, cheese, toppings);
    }

    /**
     * Returns the human-readable format of the pizza in the format:
     * <p>[MenuPizza] 'Pizza'</p>
     * Where:
     * <ul>
     *  <li>'Pizza': toString representation of the pizza class</li>
     * </ul>
     * @return string representation of the MenuPizza object
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getClass().getSimpleName(), super.toString());
    }
}