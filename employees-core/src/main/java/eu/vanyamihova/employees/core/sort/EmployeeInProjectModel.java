package eu.vanyamihova.employees.core.sort;

import java.time.LocalDate;

/**
 * Information for employee with the id and the dates, when
 * the employee has been started on a project and when he/she
 * left it.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
public final class EmployeeInProjectModel {

    private final Integer employeeId;

    private final LocalDate dateFrom;

    private final LocalDate dateTo;

    EmployeeInProjectModel(Integer employeeId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
