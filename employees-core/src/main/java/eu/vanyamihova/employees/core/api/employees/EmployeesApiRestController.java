package eu.vanyamihova.employees.core.api.employees;

import eu.vanyamihova.employees.core.api.ResponseBodyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * REST controller with only one endpoint, which requests
 * the file from which data must be converted.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@RestController
@RequestMapping(path = "/employees")
@CrossOrigin(origins = "http://localhost:4200")
public final class EmployeesApiRestController {

    @Autowired
    private EmployeesResponseBodyDataGeneratingService employeesResponseBodyDataGeneratingService;

    @PostMapping
    @ResponseBody
    public ResponseBodyData<List<EmployeesResponseBodyData>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        return employeesResponseBodyDataGeneratingService.generate(file);
    }

}
