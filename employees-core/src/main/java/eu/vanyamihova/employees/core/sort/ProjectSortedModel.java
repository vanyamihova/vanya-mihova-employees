package eu.vanyamihova.employees.core.sort;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Wrapper class, responsible for collecting and keeping the
 * data from the CSV file.
 *
 * The {@code map} is structured like:
 * - key - project id
 * - value - list with all employees which have been working on the same project
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
public final class ProjectSortedModel {

    private final Map<Integer, List<EmployeeInProjectModel>> map = new HashMap<>();

    public Stream<Map.Entry<Integer, List<EmployeeInProjectModel>>> stream() {
        return map.entrySet().stream();
    }

    void add(Integer projectId, Integer employeeId, LocalDate dateFrom, LocalDate dateTo) {
        List<EmployeeInProjectModel> models = getOnIndex(projectId);
        models.add(new EmployeeInProjectModel(employeeId, dateFrom, dateTo));
    }

    void clear() {
        map.clear();
    }

    private List<EmployeeInProjectModel> getOnIndex(Integer projectId) {
        List<EmployeeInProjectModel> models = map.get(projectId);
        if (models == null) {
            models = new ArrayList<>();
            map.putIfAbsent(projectId, models);
        }
        return models;
    }

}
