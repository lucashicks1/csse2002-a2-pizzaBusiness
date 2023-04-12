package exceptions;

/**
 * Exception thrown when an error is thrown while parsing the file using MenuLoader
 */
public class PizzaFormatException extends Exception {

    /**
     * Creates a PizzaFormatException with a detail message in the format:
     * <p>'message' at 'lineNum'</p>
     * Where:
     * <ul>
     *     <li>'message': detail message of the exception</li>
     *     <li>'lineNum": line number of the error</li>
     * </ul>
     * @param message detail message
     * @param lineNum line number of the error
     */
    public PizzaFormatException(String message, int lineNum) {
        super(String.format("%s at %s", message, lineNum));
    }

    /**
     * Creates a PizzaFormatException with cause and a message in the format:
     * <p>'message' at 'lineNum'</p>
     * Where:
     * <ul>
     *     <li>'message': detail message of the exception</li>
     *     <li>'lineNum': line number of the error</li>
     * </ul>
     * @param message detail message
     * @param lineNum line number of the error
     * @param cause throwable that caused this exception
     */
    public PizzaFormatException(String message, int lineNum, Throwable cause) {
        super(String.format("%s at %s", message, lineNum), cause);
    }

}