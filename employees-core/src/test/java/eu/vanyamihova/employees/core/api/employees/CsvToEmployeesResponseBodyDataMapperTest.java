package eu.vanyamihova.employees.core.api.employees;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJUnitConfigIntegrationTest.class)
public class CsvToEmployeesResponseBodyDataMapperTest {

    @Autowired
    private CsvToEmployeesResponseBodyDataMapper csvToEmployeesResponseBodyDataMapper;

    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private EmployeesCsvContentConverter employeesCsvContentConverter;

    @Test
    public void happy_flow() throws Exception {
        // Given
        MultipartFile multipartFile = MockMultipartFileBuilder.getFile("123, 12, 2021-01-02, 2021-01-12\n124, 12, 2021-01-10, 2021-01-12");
        CsvContent content = csvFileReader.read(employeesCsvContentConverter, multipartFile);

        // When
        List<EmployeesResponseBodyData> response = csvToEmployeesResponseBodyDataMapper.mapEntryToResponseBody(content);

        // Then
        assertNotNull(response, "List with response data should not be null");
        assertEquals(1, response.size(), "List with response data should contains only 1 element");

        EmployeesResponseBodyData responseBodyData = response.get(0);
        assertEquals(123, responseBodyData.getFirstEmployeeId(), "First employee's id should be 123");
        assertEquals(124, responseBodyData.getSecondEmployeeId(), "Second employee's id should be 124");
        assertEquals(12, responseBodyData.getProjectId(), "Project id should be 12");
        assertEquals(12, responseBodyData.getDaysWorked(), "Worked days should be 12");
    }

}
