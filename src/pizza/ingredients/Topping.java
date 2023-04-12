package pizza.ingredients;

/**
 * Represents the possible toppings that can be placed on a pizza
 */
public class Topping {

    /**
     * Name of the topping
     */
    private final String name;

    /**
     * Whether the topping is vegan or not
     */
    private final boolean vegan;

    /**
     * Price of each topping
     */
    public static final double PRICE = 2;

    /**
     * Array of all the toppings available
     */
    private static Topping[] toppings = {};

    /**
     * Constructor for the topping with a given name and whether it is vegan or not
     * @param name name of the topping
     * @param isVegan whether the topping is vegan or not
     */
    private Topping(String name, boolean isVegan) {
        this.name = name.toUpperCase();
        this.vegan = isVegan;
    }

    /**
     * Creates a new topping with a given name and vegan boolean state
     * @param name name of the topping
     * @param isVegan whether the topping is vegan or not
     * @throws IllegalArgumentException if name is null or topping with that name already exists
     */
    public static void createTopping(String name, boolean isVegan)
            throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        int n = toppings.length;
        Topping[] newToppings = new Topping[n + 1];

        // Check topping in array and add each topping to new array
        for (int i = 0; i < n; i++) {
            if (toppings[i].name.equals(name.toUpperCase())) {
                throw new IllegalArgumentException();
            }
            newToppings[i] = toppings[i];
        }
        newToppings[n] = new Topping(name.toUpperCase(), isVegan);
        toppings = newToppings;
    }

    /**
     * Returns a topping that has already been added with the specified name
     * @param name the name of the topping to be returned
     * @return the topping with the specified name
     * @throws IllegalArgumentException if the no topping exists with the specified name
     * @throws NullPointerException if the name is null
     */
    public static Topping valueOf(String name)
            throws IllegalArgumentException, NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < toppings.length; i++) {
            if (toppings[i].name.equals(name.toUpperCase())) {
                return toppings[i];
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns an array containing the toppings that have been added
     * @return array of toppings in the order they were added
     */
    public static Topping[] values() {
        return toppings;
    }

    /**
     * Resets toppings so the values() method returns an empty toppings array
     */
    public static void resetToppings() {
        toppings = new Topping[0];
    }

    /**
     * Returns the name of the topping
     * @return name of the topping
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the boolean state of whether the topping is vegan or not
     * @return whether the topping is vegan or not
     */
    public boolean isVegan() {
        return this.vegan;
    }
}