package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a pizza that has a base, sauce and up to 5 toppings
 */
public abstract class Pizza implements Bases, Sauces, Cheeses, MenuItem {

    /**
     * Maximum number of toppings on a pizza
     */
    public static final int MAX_TOPPINGS = 5;

    /**
     * Size of the pizza base
     */
    private BaseSize baseSize;

    /**
     * Sauce of the pizza
     */
    private Sauce sauce;

    /**
     * Cheese on the pizza
     */
    private Cheese cheese;

    /**
     * Name of the pizza
     */
    private String name;

    /**
     * List of toppings on the pizza
     */
    private List<Topping> toppings;

    /**
     * Creates a medium cheese pizza with no toppings, called "Dr Java's Pizza"
     */
    public Pizza() {
        this(BaseSize.MEDIUM, Sauce.TOMATO, Cheese.MOZZARELLA);

    }

    /**
     * Creates a pizza with a given size, sauce and cheese
     * @param size size of the pizza
     * @param sauce sauce on the pizza
     * @param cheese cheese on the pizza
     */
    public Pizza(Bases.BaseSize size, Sauces.Sauce sauce, Cheeses.Cheese cheese)
            throws IllegalArgumentException {
        if (size == null || sauce == null || cheese == null) {
            throw new IllegalArgumentException("Size, cheese or sauce was null");
        }
        this.baseSize = size;
        this.sauce = sauce;
        this.cheese = cheese;
        this.name = "Dr Java's Pizza";
        this.toppings = new ArrayList<Topping>();
        registerMenuItem();
    }

    /**
     * Creates a pizza with a given size, sauce, cheese and a list of toppings
     * @param size size of the pizza
     * @param sauce sauce on the pizza
     * @param cheese cheese on the pizza
     * @param toppings list of toppings on the pizza
     * @throws TooManyToppingsException if there are more than 5 toppings provided
     */
    public Pizza(Bases.BaseSize size, Sauces.Sauce sauce, Cheeses.Cheese cheese,
                 List<Topping> toppings) throws TooManyToppingsException, IllegalArgumentException {
        this(size, sauce, cheese);
        if (toppings == null) {
            throw new IllegalArgumentException("Toppings was null");
        } else if (toppings.size() > MAX_TOPPINGS) {
            throw new TooManyToppingsException("Too many toppings to add");
        }
        this.toppings = toppings;
    }

    /**
     * Returns the list of toppings that are on the pizza
     * @return list of toppings on the pizza
     */
    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Returns the cost of the base with the cost of each topping
     * @return total cost of the pizza
     */
    public double getTotalPrice() {
        return baseSize.price + toppings.size() * Topping.PRICE;
    }

    /**
     * Returns the name of the pizza
     * @return pizza name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pizza
     * @param name pizza name
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is null or blank");
        }
        this.name = name;
    }

    /**
     * Sets the pizza base
     * @param size enum depicting the sizes of each pizza base type
     */
    @Override
    public void set(BaseSize size) {
        this.baseSize = size;
    }

    /**
     * Sets the cheese on the pizza
     * @param cheese enum depicting the cheeses of each pizza
     */
    @Override
    public void set(Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * Sets the sauce on the pizza
     * @param sauce enum depicting the sauces of each pizza sauce type
     */
    @Override
    public void set(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Returns the hash code of the pizza
     * @return hash code of the pizza
     */
    @Override
    public int hashCode() {
        return baseSize.hashCode() * sauce.hashCode() * cheese.hashCode() * toppings.hashCode();
    }

    /**
     * Returns true if this pizza is equal to another given pizza
     * @param other reference object (pizza)
     * @return whether the given reference object is equal to this pizza
     */
    @Override
    public boolean equals(Object other) {
        if (this.baseSize != ((Pizza) other).baseSize) {
            return false;
        } else if (this.cheese != ((Pizza) other).cheese) {
            return false;
        } else if (this.sauce != ((Pizza) other).sauce) {
            return false;
        } else {
            return new HashSet<>(this.toppings).containsAll(((Pizza) other).toppings)
                    && ((Pizza) other).toppings.size() == this.toppings.size();
        }
    }

    /**
     * Returns the human-readable format of the pizza in the format:
     * <p>$Name: is a '`BaseSize`' sized base with '`Sauce`' sauce
     * and '`Cheese`' cheese`Toppings` $`Price`</p>
     * Where:
     * <ul>
     *  <li>'Name': name of the pizza</li>
     *  <li>'BaseSize': base size of the pizza</li>
     *  <li>'Sauce': sauce on the pizza</li>
     *  <li>'Cheese': cheese on the pizza</li>
     *  <li>Toppings: is</li>
     *  <ul>
     *      <li>Empty string if no toppings</li>
     *      <li>" - Toppings: 'Tops'" if there are toppings</li>
     *      <li>Where 'Tops' is the string representation of the toppings</li>
     *  </ul>
     *  <li>'Price': price of the pizza with two decimal places</li>
     * </ul>
     * @return string representation of the pizza object
     */
    @Override
    public String toString() {
        String toppingsList;
        if (toppings.isEmpty()) {
            toppingsList = "";
        } else {
            toppingsList = " - Toppings: [";
            for (Topping topping : toppings) {
                toppingsList = toppingsList + topping.toString() + ", ";
            }
            toppingsList = toppingsList.substring(0, toppingsList.length() - 2);
            toppingsList = toppingsList + "]";
        }
        return String.format("%s: is a '%s' sized base with '%s' sauce and '%s' cheese%s $%.2f",
                name, baseSize, sauce, cheese, toppingsList, getTotalPrice());
    }

    /**
     * Returns the list of toppings that are on this pizza.
     * @return new list of the toppings on the pizza
     */
    protected List<Topping> accessToppings() {
        return toppings;
    }
}