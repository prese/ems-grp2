package ro.sci.ems.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.TestApplicationConfiguration;
import ro.sci.ems.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationConfiguration.class})
public class EmployeeServiceTestWithConfiguration extends BaseEmployeeServiceTest {

	@Autowired
	private EmployeeService service;
	


	@Override
	protected EmployeeService getEmployeeService() {
		return service;
	}
	
	
	
}
