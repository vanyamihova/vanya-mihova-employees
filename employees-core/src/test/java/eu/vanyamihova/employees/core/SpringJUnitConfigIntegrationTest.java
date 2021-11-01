package eu.vanyamihova.employees.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EmployeesCoreApplication.class)
public class SpringJUnitConfigIntegrationTest {
}
