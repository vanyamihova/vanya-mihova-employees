package eu.vanyamihova.employees.core.api.employees;

import eu.vanyamihova.employees.core.MockMultipartFileBuilder;
import eu.vanyamihova.employees.core.SpringJUnitConfigIntegrationTest;
import eu.vanyamihova.employees.core.api.ResponseBodyData;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringJUnitConfigIntegrationTest.class)
public class EmployeesResponseBodyDataGeneratingServiceTest {

    @Autowired
    private EmployeesResponseBodyDataGeneratingService employeesResponseBodyDataGeneratingService;

    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private EmployeesCsvContentConverter employeesCsvContentConverter;

    @Test
    public void return_ok_status() throws Exception {
        // Given
        MultipartFile multipartFile = MockMultipartFileBuilder.getFile("123, 12, 2021-01-02, 2021-01-12");
        CsvContent content = csvFileReader.read(employeesCsvContentConverter, multipartFile);

        // When
        ResponseBodyData<List<EmployeesResponseBodyData>> responseBodyData = employeesResponseBodyDataGeneratingService.generate(multipartFile);

        // Then
        assertEquals("OK", responseBodyData.getStatus(), "The status should be OK");
    }

    @Test
    public void return_error_status() {
        // Given
        MultipartFile multipartFile = MockMultipartFileBuilder.getFile("123, 12, 2021-02, 2021-01-12");

        // When
        ResponseBodyData<List<EmployeesResponseBodyData>> responseBodyData = employeesResponseBodyDataGeneratingService.generate(multipartFile);

        // Then
        assertEquals("Error", responseBodyData.getStatus(), "The status should be Error");
    }

}
