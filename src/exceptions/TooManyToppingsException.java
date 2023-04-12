package exceptions;

/**
 * Exception thrown when too many toppings are attempted to be added to a pizza
 */
public class TooManyToppingsException extends Exception {

    /**
     * Creates a TooManyToppings Exception with a detail message
     * @param message detail message
     */
    public TooManyToppingsException(String message) {
        super(message);
    }

    /**
     * Creates a TooManyToppings exception with a detail message in the format:
     * <p>'message' at 'lineNum'</p>
     * Where:
     * <ul>
     *     <li>'message': detail message of the exception</li>
     *     <li>'lineNum": line number of the error</li>
     * </ul>
     * @param message detail message
     * @param lineNum line number of the error
     */
    public TooManyToppingsException(String message, int lineNum) {
        super(String.format("%s at %s", message, lineNum));
    }

    /**
     * Creates a TooManyToppings exception with a given cause
     * @param cause throwable that caused this exception
     */
    public TooManyToppingsException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a TooManyToppings exception with a detail message and the cause
     * @param message detail message
     * @param cause throwable that caused this exception
     */
    public TooManyToppingsException(String message, Throwable cause) {
        super(message, cause);
    }

}