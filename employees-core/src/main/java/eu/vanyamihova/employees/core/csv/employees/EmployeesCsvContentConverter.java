package eu.vanyamihova.employees.core.csv.employees;

import eu.vanyamihova.employees.core.csv.CsvContentConverter;
import eu.vanyamihova.employees.core.csv.CsvLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * CSV content converter specifically for the POST /employee endpoint
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
@Component
public final class EmployeesCsvContentConverter extends CsvContentConverter {

    @Override
    public CsvLine convert(String[] line) throws Exception {
        log.info("Converting the data for employee content");
        Integer employeeId = extractInteger(line, 0);
        verifyValidIntegerValue(employeeId, "Invalid employee id value: " + employeeId);
        Integer projectId = extractInteger(line, 1);
        verifyValidIntegerValue(projectId, "Invalid project id value: " + projectId);
        LocalDate dateFrom = extractLocalDate(line, 2);
        LocalDate dateTo = extractLocalDate(line, 3);
        return new EmployeesCsvLine(employeeId, projectId, dateFrom, dateTo);
    }

}
