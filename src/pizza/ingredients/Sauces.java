package pizza.ingredients;

/**
 * Interface for managing the types of sauces for each pizza
 */
public interface Sauces {

    /**
     * Set utilises an enum with all types of sauces to set the sauce of a pizza
     * @param sauce enum depicting the sauces of each pizza sauce type
     */
    void set(Sauces.Sauce sauce);

    /**
     * Enum containing teh cheeses available for the pizza sauce type
     */
    enum Sauce {
        /**
         * Classic tomato sauce
         */
        TOMATO,

        /**
         * BBQ Sauce
         */
        BBQ,

        /**
         * Strong smelling Garlic sauce
         */
        GARLIC,

        /**
         * No sauce option
         */
        NONE;
    }
}