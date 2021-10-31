package eu.vanyamihova.employees.core.exception;

/**
 * Exception, thrown when the format of the dates
 * from the ingested file is not supported.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
public class NotSupportedDateFormatException extends RuntimeException {

    public NotSupportedDateFormatException(String message) {
        super(message);
    }

}
