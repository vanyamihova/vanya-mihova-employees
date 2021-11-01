package eu.vanyamihova.employees.core.api.employees;

import eu.vanyamihova.employees.core.csv.CsvContent;
import eu.vanyamihova.employees.core.exception.NotSupportedDurationException;
import eu.vanyamihova.employees.core.sort.EmployeeInProjectModel;
import eu.vanyamihova.employees.core.sort.ProjectSortedModel;
import eu.vanyamihova.employees.core.sort.ProjectSortingComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Component, responsible for correct mapping of the ingested data
 * and converting it to corresponding response body
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
@Component
public final class CsvToEmployeesResponseBodyDataMapper {

    @Autowired
    private ProjectSortingComponent projectSortingComponent;

    public List<EmployeesResponseBodyData> mapEntryToResponseBody(CsvContent content) {
        log.info("Start mapping from file to response body");
        ProjectSortedModel projectSortedModel = projectSortingComponent.sort(content);
        return projectSortedModel.stream()
                .map(this::mapEntryToResponseBody)
                .collect(Collectors.toList());
    }

    private EmployeesResponseBodyData mapEntryToResponseBody(Map.Entry<Integer, List<EmployeeInProjectModel>> entry) {
        Integer projectId = entry.getKey();
        List<EmployeeInProjectModel> employees = entry.getValue();
        Integer firstEmployeeId = extractEmployeeIdByIndex(0, employees);
        Integer secondEmployeeId = extractEmployeeIdByIndex(1, employees);
        Long daysWorked = calculateTotalDaysWorked(employees);
        return new EmployeesResponseBodyData(firstEmployeeId, secondEmployeeId, projectId, daysWorked);
    }

    private Integer extractEmployeeIdByIndex(int index, List<EmployeeInProjectModel> employees) {
        if (employees.size() <= index) {
            log.warn("There is no employee on index: {}", index);
            return null;
        }
        Integer employeeId = employees.get(index).getEmployeeId();
        log.info("Successfully extracted employee id: {}", employeeId);
        return employeeId;
    }

    private Long calculateTotalDaysWorked(List<EmployeeInProjectModel> employees) {
        Long totalDays = employees.stream()
                .map(this::calculateDaysWorked)
                .reduce(0L, Long::sum);
        if (totalDays < 0) {
            throw new NotSupportedDurationException("Somebody is working too much");
        }
        log.info("Successfully calculated worked days: {}", totalDays);
        return totalDays;
    }

    private Long calculateDaysWorked(EmployeeInProjectModel employee) {
        Duration duration = Duration.between(employee.getDateFrom().atStartOfDay(), employee.getDateTo().atStartOfDay());
        log.debug("Calculated duration {} days for employee with id {} for dates {} - {}",
                duration.toDays(), employee.getEmployeeId(), employee.getDateFrom(), employee.getDateTo());
        return duration.toDays();
    }

}
