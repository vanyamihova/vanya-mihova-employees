package eu.vanyamihova.employees.core.csv.employees;

import eu.vanyamihova.employees.core.csv.CsvLine;
import lombok.Getter;

import java.time.LocalDate;

/**
 * This represents the CSV content line specifically from the POST /employee endpoint
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Getter
public final class EmployeesCsvLine implements CsvLine {

    private Integer employeeId;

    private Integer projectId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public EmployeesCsvLine(Integer employeeId, Integer projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean isEmpty() {
        return employeeId == null || projectId == null || dateFrom == null;
    }

}
