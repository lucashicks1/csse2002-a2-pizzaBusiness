package menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class which defines the menu where items can be ordered from
 */
public class Menu {

    /**
     * singleton instance of the menu class
     */
    private static Menu instance;

    /**
     * List of menu items on the menu
     */
    private static ArrayList<MenuItem> menuItemList = new ArrayList<>();

    private Menu(){

    }

    /**
     * Returns the singleton instance of the menu
     * @return singleton instance of the menu
     */
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    /**
     * Returns a list of the menu items that have been added to the menu
     * @return new copy of the list of menu items
     */
    public List<MenuItem> getItems() {
        return new ArrayList<>(menuItemList);
    }

    /**
     * Registers a menu item with this menu if it hasn't been seen before
     * @param item menu item to be added to the menu
     */
    public void registerMenuItem(MenuItem item) {
        if (!menuItemList.contains(item)) {
            menuItemList.add(item);
        }
    }

    /**
     * Returns a menu item from the list of menu items on the menu
     * @param index index of the menu item in the list
     * @return the item that was found
     * @throws IndexOutOfBoundsException if the array is null or the index doesn't exist
     */
    public MenuItem get(int index) throws IndexOutOfBoundsException {
        if (menuItemList == null || index > (menuItemList.size() - 1)) {
            throw new IndexOutOfBoundsException();
        }
        return menuItemList.get(index);
    }

    /**
     * Removes all loaded menu items from the menu
     */
    public void clear() {
        menuItemList = new ArrayList<>();
    }
}