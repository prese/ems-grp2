package ro.sci.ems.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.sci.ems.dao.EmployeeDAO;
import ro.sci.ems.domain.Employee;

@Service
public class EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeDAO dao;

	public Collection<Employee> listAll() {
		return dao.getAll();
	}

	public Collection<Employee> search( String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting employee for id: " + id);
		Employee emp = dao.findById(id);
		if (emp != null) {
			dao.delete(emp);
			return true;
		}

		return false;
	}

	public Employee get(Long id) {
		LOGGER.debug("Getting employee for id: " + id);
		return dao.findById(id);

	}

	public void save(Employee employee) throws ValidationException {
		LOGGER.debug("Saving: " + employee);
		validate(employee);

		dao.update(employee);
	}

	private void validate(Employee employee) throws ValidationException {
		Date currentDate = new Date();
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(employee.getFirstName())) {
			errors.add("First Name is Empty");
		}

		if (StringUtils.isEmpty(employee.getLastName())) {
			errors.add("Last Name is Empty");
		}

		if (employee.getGender() == null) {
			errors.add("Gender is Empty");
		}

		if (StringUtils.isEmpty(employee.getJobTitle())) {
			errors.add("JobTitle is Empty");
		}

		if (employee.getBirthDate() == null) {
			errors.add("BirthDate is Empty");
		} else {
			if (currentDate.before(employee.getBirthDate())) {
				errors.add("Birthdate in future");
			}
			
			Calendar c = GregorianCalendar.getInstance();
			c.setTime(new Date());
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 18);
			if (employee.getBirthDate().after(c.getTime())) {
				errors.add("Too young to get employeed");
			}
			
			c.set(Calendar.YEAR, 1901);
			if (employee.getBirthDate().before(c.getTime())) {
				errors.add("Too old to get employeed");
			}
			
		}

		if (employee.getEmploymentDate() == null) {
			errors.add("EmploymentDate is Empty");
		} else {
			if (currentDate.before(employee.getEmploymentDate())) {
				errors.add("EmploymentDate in future");
			}
		}
		
		if (employee.getBirthDate() != null && employee.getEmploymentDate() != null) {
			if (employee.getEmploymentDate().before(employee.getBirthDate())) {
				errors.add("EmploymentDate before BirthDate");
			}
		}
		
		

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public EmployeeDAO getDao() {
		return dao;
	}

	public void setDao(EmployeeDAO dao) {
		this.dao = dao;
	}
	
	
}
