package eu.vanyamihova.employees.core.sort;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectSortedModelTest {

    @Test
    public void add_new_data_to_project_sort_model() {
        // Given
        ProjectSortedModel projectSortedModel = new ProjectSortedModel();

        // When
        projectSortedModel.add(10, 123, LocalDate.of(2021, 2, 3),  LocalDate.of(2021, 2, 13));

        // Then
        assertEquals(1, projectSortedModel.stream().count(), "There should be only 1 element in the sorted model");
    }

}
