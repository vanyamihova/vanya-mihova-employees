package eu.vanyamihova.employees.core.csv.employees;

import eu.vanyamihova.employees.core.exception.InvalidIntegerValueException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeesCsvContentConverterTest {

    private EmployeesCsvContentConverter converter = new EmployeesCsvContentConverter();

    @Test
    public void convert_correct_content_line() throws Exception {
        // Given
        String[] contentLine = new String[]{"123", "54", "2021-05-01", "2021-06-12"};

        // When
        EmployeesCsvLine csvLine = (EmployeesCsvLine) converter.convert(contentLine);

        // Then
        assertEquals(123, csvLine.getEmployeeId(), "The employee id should be 123");
        assertEquals(54, csvLine.getProjectId(), "The project id should be 54");
        assertEquals(LocalDate.of(2021, 5, 1), csvLine.getDateFrom(), "The start date should be '2021-05-01'");
        assertEquals(LocalDate.of(2021, 6, 12), csvLine.getDateTo(), "The end date should be '2021-06-12'");
    }

    @Test
    public void convert_content_line_with_null_date_to() throws Exception {
        // Given
        String[] contentLine = new String[]{"123", "54", "2021-05-01", "NULL"};

        // When
        EmployeesCsvLine csvLine = (EmployeesCsvLine) converter.convert(contentLine);

        // Then
        assertEquals(123, csvLine.getEmployeeId(), "The employee id should be 123");
        assertEquals(54, csvLine.getProjectId(), "The project id should be 54");
        assertEquals(LocalDate.of(2021, 5, 1), csvLine.getDateFrom(), "The start date should be '2021-05-01'");
        assertEquals(LocalDate.now(), csvLine.getDateTo(), "The end date should be 'NOW'");
    }

    @Test(expected = InvalidIntegerValueException.class)
    public void convert_content_line_with_empty_employee_id() throws Exception {
        // Given
        String[] contentLine = new String[]{null, "123", "2021-05-01", "NULL"};

        // When
        converter.convert(contentLine);
    }


    @Test(expected = InvalidIntegerValueException.class)
    public void convert_content_line_with_empty_project_id() throws Exception {
        // Given
        String[] contentLine = new String[]{"123", null, "2021-05-01", "NULL"};

        // When
        converter.convert(contentLine);
    }

}
