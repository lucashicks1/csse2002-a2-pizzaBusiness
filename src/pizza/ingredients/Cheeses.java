package pizza.ingredients;

/**
 * Interface for managing the types of cheeses available
 */
public interface Cheeses {

    /**
     * Set utilises an enum with all types of cheese to set the cheese of a pizza
     * @param cheese enum depicting the cheeses of each pizza
     */
    void set(Cheeses.Cheese cheese);

    /**
     * Enum containing the cheeses available for the pizza cheese type
     */
    enum Cheese {
        /**
         * Classic shredded Mozzarella cheese
         */
        MOZZARELLA,

        /**
         * Represents the no cheese option
         */
        NONE,

        /**
         * Vegan cheese
         */
        VEGAN;
    }
}