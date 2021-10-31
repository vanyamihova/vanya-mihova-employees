package eu.vanyamihova.employees.core.utils;


import eu.vanyamihova.employees.core.exception.NotSupportedDateFormatException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DateFormatCollectionTest {

    @Test
    public void parse_format_yyyy_mm_dd() {
        // Given
        String dateAsString = "2021-01-21";

        // When
        String format = DateFormatCollection.findFormat(dateAsString);

        // Then
        assertThat("The format should be 'yyyy-MM-dd'", format, is("yyyy-MM-dd"));
    }

    @Test(expected = NotSupportedDateFormatException.class)
    public void parse_not_supported_date_format() {
        // Given
        String dateAsString = "2021-21";

        // When
        DateFormatCollection.findFormat(dateAsString);
    }

}
