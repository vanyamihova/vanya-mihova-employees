package eu.vanyamihova.employees.core.utils;

import eu.vanyamihova.employees.core.exception.NotSupportedDateFormatException;
import lombok.extern.slf4j.Slf4j;

/**
 * Holds all date formats which are supported from the application.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
public enum DateFormatCollection {

    FORMAT_1("^\\d{8}$", "yyyyMMdd"),
    FORMAT_2("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy"),
    FORMAT_3("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd"),
    FORMAT_4("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy"),
    FORMAT_5("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd"),
    FORMAT_6("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy"),
    FORMAT_7("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");

    private String pattern;

    private String format;

    DateFormatCollection(String pattern, String format) {
        this.pattern = pattern;
        this.format = format;
    }

    public static String findFormat(String dateAsString) {
        for (DateFormatCollection item : DateFormatCollection.values()) {
            if (dateAsString.matches(item.pattern)) {
                log.info("Date formatter has been found {}", item.format);
                return item.format;
            }
        }
        throw new NotSupportedDateFormatException("Not supported format of date: " + dateAsString);
    }

}
