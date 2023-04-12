package io;

import exceptions.PizzaFormatException;
import exceptions.TooManyToppingsException;
import menu.Menu;
import pizza.MenuPizza;
import pizza.Pizza;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for loading, saving and parsing text data retrieved from pizza menus in assets
 */
public class MenuLoader {

    /**
     * folder location for the pizza menus
     */
    public static final String PATH = "./src/assets/";

    /**
     * Integer exit code when file could not be open
     */
    static final int COULD_NOT_OPEN_FILE = 1;

    /**
     * Integer exit code for when file is formatted incorrectly
     */
    static final int FILE_FORMAT_ERROR = 2;

    /**
     * Integer exit code for when too many toppings are on a pizza
     */
    static final int TOO_MANY_TOPPINGS = 4;

    /**
     * Integer exit code for when number of pizzas in file doesn't match pizza count
     */
    static final int MISSING_NUMBER_OF_PIZZAS = 5;

    /**
     * Integer exit code for when line could not be read
     */
    static final int CANNOT_READ_LINE = 6;

    /**
     * Creates a bufferedReader using a FileReader with a given file name
     * @param filename file to be read
     * @return parsed Menu type containing the list of Pizzas found in the menu text file
     */
    public static Menu load(String filename) {

        Menu menu = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH + filename));
            menu = getMenu(reader);
        } catch (FileNotFoundException e) {
            System.out.println(COULD_NOT_OPEN_FILE);
        } catch (PizzaFormatException e) {
            System.out.println(e);
        } catch (TooManyToppingsException e) {
            System.out.println(TOO_MANY_TOPPINGS);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MISSING_NUMBER_OF_PIZZAS);
        } catch (IOException e) {
            System.out.println(CANNOT_READ_LINE);
        }
        return menu;
    }

    /**
     * Loads the data from the pizza menu file into the Menu
     * @param reader Buffered reader used to read file
     * @return Menu that has all of the pizzas loaded from the file
     * @throws PizzaFormatException if the reader is null or empty, if the name on the
     * first line is not "PizzaMenu", if the space is missing after the name, if the
     * number of pizzas can't be parsed, if a blank line does not follow the first line,
     * if a topping line contains an invalid topping name, if a blank line does not
     * follow the vegan topping line, if a pizza line contains an invalid topping (such
     * that, it was not mentioned in any topping line).
     * @throws TooManyToppingsException if a pizza line has too many toppings
     * @throws IOException if an error occurs when trying to read the file
     * @throws IndexOutOfBoundsException if the nuber of pizza lines given in the first
     * line does not match the number of pizza lines present in the file.
     */
    public static Menu getMenu(BufferedReader reader)
            throws PizzaFormatException, TooManyToppingsException,
            IOException, IndexOutOfBoundsException {

        int lineNum = 0;
        List<Pizza> pizzas;

        try {

            if (reader == null || !reader.ready()) {
                throw new PizzaFormatException("Reader is null or not ready", lineNum);
            }

            String line = reader.readLine();
            if (line == null) {
                throw new PizzaFormatException("No lines in file", lineNum);
            }

            lineNum++;
            if (!line.startsWith("PizzaMenu")) {
                throw new PizzaFormatException("File does not start with PizzaMenu", lineNum);
            }

            String[] pair = line.split(" ", 2);

            if (pair.length != 2) {
                throw new PizzaFormatException("Missing Space", lineNum);
            } else if (!pair[0].equals("PizzaMenu")) {
                throw new PizzaFormatException("Initial Keyword is incorrect", lineNum);
            }

            int numPizzas;

            try {
                numPizzas = Integer.parseInt(pair[1]);
            } catch (NumberFormatException e) {
                throw new PizzaFormatException("Missing number of Pizzas", lineNum);
            }

            pizzas = new ArrayList<>(numPizzas);

            line = reader.readLine();
            lineNum++;

            if (line == null || !line.equals("")) {
                throw new PizzaFormatException("Line should be empty", lineNum);
            }

            line = reader.readLine();
            lineNum++;
            String[] nonVeganToppings = line.split(", ");
            try {
                for (String name : nonVeganToppings) {
                    Topping.createTopping(name, false);
                }
            } catch (IllegalArgumentException e) {
                throw new PizzaFormatException("Null or duplicate topping name", lineNum);
            }

            line = reader.readLine();
            lineNum++;
            String[] veganToppings = line.split(", ");
            try {
                for (String name : veganToppings) {
                    Topping.createTopping(name, true);
                }
            } catch (IllegalArgumentException e) {
                throw new PizzaFormatException("Null or duplicate topping name", lineNum);
            }

            line = reader.readLine();
            lineNum++;

            if (line == null || !line.equals("")) {
                throw new PizzaFormatException("Line should be empty", lineNum);
            }

            for (int i = 0; i < numPizzas; i++) {
                line = reader.readLine();
                if (line == null) {
                    throw new IndexOutOfBoundsException("Incorrect number of pizzas specified");
                }
                lineNum++;

                String[] pizzaPair = line.split(" \\[", 2);
                if (pizzaPair.length != 2) {
                    throw new PizzaFormatException("Incorrect spacing", lineNum);
                } else if (pizzaPair[1].charAt(pizzaPair[1].length() - 1) != ']') {
                    throw new PizzaFormatException(
                            "Toppings list doesn't end with a square bracket", lineNum);
                }
                String[] toppingNames =
                        pizzaPair[1].substring(0, pizzaPair[1].length() - 1).split(", ");
                try {
                    ArrayList<Topping> toppings = getToppings(toppingNames);
                    MenuPizza pizza = new MenuPizza(
                            Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO,
                            Cheeses.Cheese.MOZZARELLA, toppings);
                    pizza.setName(pizzaPair[0]);
                } catch (IllegalArgumentException e) {
                    throw new PizzaFormatException("Invalid topping name", lineNum);
                } catch (NullPointerException e) {
                    throw new PizzaFormatException("Null topping name", lineNum);
                }


            }

            line = reader.readLine();

            if (line != null) {
                throw new IndexOutOfBoundsException(
                        "Listed number of pizzas doesn't match pizza list");
            }

        } catch (IOException e) {
            throw new IOException("Couldn't read line because of " + e);
        }
        return Menu.getInstance();
    }

    /**
     * Returns a list of toppings given an array of topping names
     * @param toppingNames array of topping names
     * @return list of toppings
     * @throws IllegalArgumentException if no topping exists for a given topping name
     * @throws NullPointerException if the argument is null
     */
    private static ArrayList<Topping> getToppings(String[] toppingNames)
            throws IllegalArgumentException, NullPointerException {
        ArrayList<Topping> toppingList = new ArrayList<>();
        for (String toppingName : toppingNames) {
            toppingList.add(Topping.valueOf(toppingName));
        }
        return toppingList;
    }


}