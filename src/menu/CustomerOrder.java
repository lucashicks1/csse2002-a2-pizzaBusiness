package menu;

import exceptions.TooManyToppingsException;

/**
 * Class used for creating pizzas and storing orders
 */
public class CustomerOrder {

    /**
     * Order of pizzas
     */
    private Order order;

    /**
     * Customer's name
     */
    private String customerName;

    /**
     * Default constructor to create a pizza, which calls a GUI prompt to get the name
     */
    public CustomerOrder() {
        this("Not Given");
        this.customerName = requestName();
        try {
            order = createOrder();
            order.setName(customerName);
        } catch (TooManyToppingsException e) {
            // Blank line to just catch exception
        }
    }

    /**
     * Constructor with name as parameter, and stores unique identifier, and current date and time
     * @param customerName name of the customer
     */
    public CustomerOrder(String customerName) {
        this.customerName = customerName;
        try {
            order = createOrder();
            order.setName(customerName);
        } catch (TooManyToppingsException e) {
            // Blank line to just catch exception
        }
    }

    /**
     * Creates a test customer order
     * @return order containing the entire order of the customer
     * @throws TooManyToppingsException if toppings are attempted to be added to the pizza
     */
    public Order createOrder() throws TooManyToppingsException {
        order = new Order();
        return order;
    }

    /**
     * Returns the human-readable representation of the customer order in the format:
     * <p>Customer Order {'order'}</p>
     * Where:
     * <ul>
     *     <li>'order': string representation of the order</li>
     * </ul>
     * @return string representation of the customer's order
     */
    @Override
    public String toString() {
        return String.format("Customer Order {%s}", order.toString());
    }

    /**
     * Prompts the user for their name
     * @return the name of the customer
     */
    protected String requestName() {
        String name = javax.swing.JOptionPane.showInputDialog("What is your name?");
        if (name == null) {
            name = requestName();
        }
        return name;
    }

}