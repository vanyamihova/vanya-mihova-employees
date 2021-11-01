package eu.vanyamihova.employees.core.exception;

/**
 * Thrown, when there is an invalid value for an integer.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 01.11.2021
 */
public class InvalidIntegerValueException extends RuntimeException {

    public InvalidIntegerValueException(String message) {
        super(message);
    }
}
