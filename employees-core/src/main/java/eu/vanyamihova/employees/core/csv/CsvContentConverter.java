package eu.vanyamihova.employees.core.csv;

import eu.vanyamihova.employees.core.utils.DateFormatCollection;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parent of all CSV content converters. It holds some common methods
 * for extracting Integer and Date from the CSV content.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
public abstract class CsvContentConverter {

    public abstract CsvLine convert(String[] line);

    protected LocalDate extractLocalDate(String[] line, int index) {
        if (line.length <= index) {
            return null;
        }
        String dateAsString = line[index].trim();
        if ("NULL".equalsIgnoreCase(dateAsString)) {
            log.info("The date has value of `NULL`. The NOW() will be set");
            return LocalDate.now();
        }
        try {
            DateTimeFormatter formatter = createFormatter(dateAsString);
            return LocalDate.parse(dateAsString, formatter);
        } catch (DateTimeParseException exception) {
            log.error("Error while parsing date '{}' : {}", dateAsString, exception.getMessage());
            return null;
        }
    }

    protected Integer extractInteger(String[] line, int index) {
        if (line.length <= index) {
            return null;
        }
        return Integer.parseInt(line[index].trim());
    }

    private DateTimeFormatter createFormatter(String dateAsString) {
        String format = DateFormatCollection.findFormat(dateAsString);
        return DateTimeFormatter.ofPattern(format);
    }

}
