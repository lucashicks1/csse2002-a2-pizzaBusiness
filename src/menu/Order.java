package menu;

import pizza.Pizza;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Order object to represent an individual order
 */
public class Order {

    /**
     * Lambda functional interface to apply a 10% discount
     */
    public static final MenuItem.Discount DISCOUNT_10 = (price) -> price * 0.9;

    /**
     * Lambda functional interface to apply a 25% discount
     */
    public static final MenuItem.Discount DISCOUNT_25 = (price) -> price * 0.75;

    /**
     * name of the customer
     */
    private String name;

    /**
     * The orders UUID (universally unique identifier)
     */
    private UUID uuid;

    /**
     * The order date
     */
    private LocalDate date;

    /**
     * The order time
     */
    private LocalTime time;

    /**
     * List of pizzas in the order
     */
    private ArrayList<Pizza> pizzas;

    /**
     * Creates an order, initialising the UUID, time, date and list of pizzas
     */
    public Order() {
        name = "Not Given";
        uuid = UUID.randomUUID();
        date = LocalDate.now();
        time = LocalTime.now();
        pizzas = new ArrayList<>();
    }

    /**
     * Sets the order's customer name
     * @param name name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the order's UUID
     * @param uuid unique identifier
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets the order's date
     * @param date LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the order's time
     * @param time LocalTime
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Adds a completed pizza to the order
     * @param pizza pizza to be added
     */
    public void add(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Returns the human-readable format of the order in the format:
     * <p>Date: 'date'</p>
     * <p>Time: 'time'</p>
     * <p>Customer: 'name'</p>
     * <p>Order number: 'id'</p>
     * <p>Order:</p>
     * <p>'num' - 'toString'</p>
     * Where:
     * <ul>
     *  <li>'date': Date of the order</li>
     *  <li>'time': Time of the order formatted to the hour and minute</li>
     *  <li>'name': name of the customer order</li>
     *  <li>'id': unique id for the order</li>
     *  <li>'num': incrementing number for each pizza</li>
     *  <li>'toString': toString for the pizza</li>
     * </ul>
     * @return string representation of the pizza object
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder out = new StringBuilder();
        out.append("Date: " + date.toString() + System.lineSeparator());
        out.append("Time: " + time.format(formatter) + System.lineSeparator());
        out.append("Customer: " + name + System.lineSeparator());
        out.append("Order number: " + uuid + System.lineSeparator());
        out.append("Order:");

        int pizzaTally = 1;
        double totalCost = 0;

        for (Pizza pizza : pizzas) {
            out.append(String.format(
                    "%s%s - %s", System.lineSeparator(), pizzaTally, pizza.toString()
            ));

            totalCost += pizza.getTotalPrice();
            pizzaTally++;
        }

        // Check for order discount and add to string builder
        if (pizzas.size() >= 3) {
            out.append(System.lineSeparator());
            double discountCost;
            if (pizzas.size() >= 6) {
                discountCost = DISCOUNT_25.applyDiscount(totalCost);
            } else {
                discountCost = DISCOUNT_10.applyDiscount(totalCost);
            }
            out.append(String.format(
                    "%sMulti item discount applied of $%.2f applied, new Total: $%.2f",
                    System.lineSeparator(), totalCost, discountCost)
            );
        } else {
            out.append(System.lineSeparator());
            out.append(System.lineSeparator());
            out.append(String.format("Total: $%.2f", totalCost));
        }
        out.append(System.lineSeparator());
        return out.toString();
    }
}