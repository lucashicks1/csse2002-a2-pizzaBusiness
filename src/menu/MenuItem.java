package menu;

/**
 * Interface that represents an item that can be ordered from the menu
 */
public interface MenuItem {

    /**
     * Functional interface that allows for the pizza cost to be discounted
     */
    @FunctionalInterface
    interface Discount {

        /**
         * Applies a discount to the given price
         * @param price the input price
         * @return the discounted price
         */
        double applyDiscount(double price);
    }

    /**
     * Returns the price of the menu item
     * @return menu item price
     */
    double getTotalPrice();

    /**
     * Registers this item with the singleton menu class
     */
    default void registerMenuItem() {
        Menu.getInstance().registerMenuItem(this);
    }

    /**
     * Returns the name of this menu item
     * @return menu item name
     */
    String getName();

}