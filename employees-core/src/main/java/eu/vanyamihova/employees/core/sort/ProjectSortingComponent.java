package eu.vanyamihova.employees.core.sort;

import eu.vanyamihova.employees.core.csv.CsvContent;
import eu.vanyamihova.employees.core.csv.employees.EmployeesCsvLine;
import org.springframework.stereotype.Component;

/**
 * Component, responsible for building correct {@link ProjectSortedModel}.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Component
public class ProjectSortingComponent {

    private final ProjectSortedModel projectSortedModel = new ProjectSortedModel();

    public ProjectSortedModel sort(CsvContent csvContent) {
        projectSortedModel.clear();
        csvContent.getLines()
                .stream()
                .map(csvLine -> (EmployeesCsvLine) csvLine)
                .forEach(this::addToModel);
        return projectSortedModel;
    }

    private void addToModel(EmployeesCsvLine employeesCsvLine) {
        projectSortedModel.add(employeesCsvLine.getProjectId(),
                employeesCsvLine.getEmployeeId(),
                employeesCsvLine.getDateFrom(),
                employeesCsvLine.getDateTo());
    }

}
