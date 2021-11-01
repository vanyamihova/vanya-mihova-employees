package eu.vanyamihova.employees.core.sort;

import eu.vanyamihova.employees.core.MockMultipartFileBuilder;
import eu.vanyamihova.employees.core.SpringJUnitConfigIntegrationTest;
import eu.vanyamihova.employees.core.csv.CsvContent;
import eu.vanyamihova.employees.core.csv.CsvFileReader;
import eu.vanyamihova.employees.core.csv.employees.EmployeesCsvContentConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJUnitConfigIntegrationTest.class)
public class ProjectSortingComponentTest {

    @Autowired
    private ProjectSortingComponent projectSortingComponent;

    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private EmployeesCsvContentConverter employeesCsvContentConverter;

    @Test
    public void happy_flow() throws Exception {
        // Given
        MultipartFile multipartFile = MockMultipartFileBuilder.getFile("123, 12, 2021-01-02, 2021-02-01");
        CsvContent content = csvFileReader.read(employeesCsvContentConverter, multipartFile);

        // When
        ProjectSortedModel projectSortedModel = projectSortingComponent.sort(content);

        // Then
        assertNotNull(projectSortedModel, "The model should not be null");

        Map.Entry<Integer, List<EmployeeInProjectModel>> entry = projectSortedModel.stream().findFirst().orElse(null);
        assertNotNull(entry, "The entry should not be null");
        assertEquals(12, entry.getKey(), "The key should be same as the project id - 12");

        List<EmployeeInProjectModel> employees = entry.getValue();
        assertEquals(1, employees.size(), "The employees list should have size of 1");

        EmployeeInProjectModel model = employees.get(0);
        assertEquals(123, model.getEmployeeId(), "The employee id should be 123");
        assertEquals(LocalDate.of(2021, 1, 2), model.getDateFrom(), "The employee start date should be 2021-01-02");
        assertEquals(LocalDate.of(2021, 2, 1), model.getDateTo(), "The employee end date should be 2021-02-01");
    }

}
