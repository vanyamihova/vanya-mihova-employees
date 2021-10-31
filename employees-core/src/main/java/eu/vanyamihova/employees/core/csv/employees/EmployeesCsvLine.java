package eu.vanyamihova.employees.core.csv.employees;

import eu.vanyamihova.employees.core.csv.CsvLine;

import java.time.LocalDate;

/**
 * This represents the CSV content line specifically from the POST /employee endpoint
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
