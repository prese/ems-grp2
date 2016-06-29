package ro.sci;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.sci.ems.dao.EmployeeDAO;
import ro.sci.ems.dao.inmemory.IMEmployeeDAO;
import ro.sci.ems.service.EmployeeService;

@Configuration
public class TestApplicationConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new EmployeeService();
	}
	
	@Bean
	public EmployeeDAO employeeDAO() {
		return new IMEmployeeDAO();
	
		
	}
}
