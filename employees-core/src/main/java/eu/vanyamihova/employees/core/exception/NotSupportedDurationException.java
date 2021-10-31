package eu.vanyamihova.employees.core.exception;

/**
 * Exception, thrown when the calculated duration is out of
 * the range of the variable in which it needs to be fit.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
public class NotSupportedDurationException extends RuntimeException {

    public NotSupportedDurationException(String message) {
        super(message);
    }

}
