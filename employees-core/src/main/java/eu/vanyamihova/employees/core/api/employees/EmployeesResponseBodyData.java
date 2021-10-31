package eu.vanyamihova.employees.core.api.employees;

import lombok.Getter;

/**
 * POJO, presenting the response body for POST /employee endpoint
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Getter
public final class EmployeesResponseBodyData {

    private final Integer firstEmployeeId;

    private final Integer secondEmployeeId;

    private final Integer projectId;

    private final Long daysWorked;

    public EmployeesResponseBodyData(Integer firstEmployeeId, Integer secondEmployeeId, Integer projectId, Long daysWorked) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

}
