package pizza.ingredients;

/**
 * Basic interface for managing the types of base sizes available
 */
public interface Bases {

    /**
     * Set utilises an enum with all types of bases to set the base of a pizza
     * @param size enum depicting the sizes of each pizza base type
     */
    void set(Bases.BaseSize size);

    /**
     * Enum containing the sizes available for the pizza Bases type
     */
    enum BaseSize {
        /**
         * Small pizza base - $3
         */
        SMALL(3),

        /**
         * Medium pizza base - $5
         */
        MEDIUM(5),

        /**
         * Large pizza base - $7
         */
        LARGE(7);

        /**
         * Price of the pizza base ($)
         */
        public final double price;

        private BaseSize(double price) {
            this.price = price;
        }

        /**
         * Returns the price of the pizza base
         * @return the price of the base
         */
        public double getPrice() {
            return this.price;
        }
    }
}