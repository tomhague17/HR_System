/**
 * Represents a custom exception that extends Exception and is thrown when an invalid input is provided, such as
 * entering a value less than or equal to zero.
 */

public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("Please enter a number greater than 0");
    }

    public InvalidInputException(String s) {
        super(s);
    }



}
