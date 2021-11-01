package eu.vanyamihova.employees.core.api.employees;

import eu.vanyamihova.employees.core.api.GenerateResponseBodyService;
import eu.vanyamihova.employees.core.api.ResponseBodyData;
import eu.vanyamihova.employees.core.csv.CsvContent;
import eu.vanyamihova.employees.core.csv.CsvFileReader;
import eu.vanyamihova.employees.core.csv.employees.EmployeesCsvContentConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service, responsible for building the response for POST /employee
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
@Component
public final class EmployeesResponseBodyDataGeneratingService extends GenerateResponseBodyService<List<EmployeesResponseBodyData>> {

    @Autowired
    private CsvFileReader csvFileReader;

    @Autowired
    private EmployeesCsvContentConverter employeesCsvContentConverter;

    @Autowired
    private CsvToEmployeesResponseBodyDataMapper csvToEmployeesResponseBodyDataMapper;

    public ResponseBodyData<List<EmployeesResponseBodyData>> generate(MultipartFile file) {
        try {
            List<EmployeesResponseBodyData> responseBodyData = getResponseBody(file);
            return getOkResponseBody(responseBodyData);
        } catch (Exception e) {
            log.error("Error while generating the response: {}", e.getMessage());
            return getErrorResponseBody(e.getMessage());
        }
    }

    private List<EmployeesResponseBodyData> getResponseBody(MultipartFile file) throws Exception {
        CsvContent content = csvFileReader.read(employeesCsvContentConverter, file);
        return csvToEmployeesResponseBodyDataMapper.mapEntryToResponseBody(content);
    }

}
