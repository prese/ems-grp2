package ro.sci.ems.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ro.sci.ems.domain.Employee;
import ro.sci.ems.domain.Gender;
import ro.sci.ems.service.EmployeeService;
import ro.sci.ems.service.ValidationException;

public abstract class BaseEmployeeServiceTest {

	protected abstract EmployeeService getEmployeeService();

	@After
	public void tearDown() {
		Collection<Employee> employees = new LinkedList<Employee>(getEmployeeService().listAll());

		for (Employee employee : employees) {
			getEmployeeService().delete(employee.getId());
		}
	}

	@Test
	public void test_empty_get_all() {
		Collection<Employee> employees = getEmployeeService().listAll();
		Assert.assertTrue(employees.isEmpty());
	}

	@Test(expected = ValidationException.class)
	public void test_add_no_first_name() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setLastName("Babanan");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_last_name() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_too_young() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 17);
		em.setBirthDate(c.getTime());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_too_old() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, 1900);
		em.setBirthDate(c.getTime());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_too_employment_date_before_birth_date() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 20);
		em.setBirthDate(c.getTime());
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 21);
		em.setEmploymentDate(c.getTime());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_birthdate() throws ValidationException {
		Employee em = new Employee();
		// em.setBirthDate(new Date());
		em.setEmploymentDate(new Date(70, 1, 1));
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_employment_date() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		// em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_gender() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		// em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_job_title() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		// em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_empty() throws ValidationException {
		Employee em = new Employee();

		getEmployeeService().save(em);

	}

	@Test
	public void test_add_valid_employee() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(1, getEmployeeService().listAll().size());
		Employee fromDB = getEmployeeService().listAll().iterator().next();
		Assert.assertNotNull(fromDB);
		Assert.assertTrue(fromDB.getId() > 0);
		em.setId(fromDB.getId());
		Assert.assertEquals(em, fromDB);
	}

	@Test
	public void test_delete_inexistent() throws ValidationException {

		Assert.assertFalse(getEmployeeService().delete(-10l));

	}

	@Test
	public void test_add_delete() throws Exception {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(1, getEmployeeService().listAll().size());
		Employee fromDB = getEmployeeService().listAll().iterator().next();

		Assert.assertTrue(getEmployeeService().delete(fromDB.getId()));
		Assert.assertFalse(getEmployeeService().delete(fromDB.getId()));
		Assert.assertEquals(0, getEmployeeService().listAll().size());

	}

	@Test
	public void test_search_by_name_no_result() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(0, getEmployeeService().search("cucu").size());

	}

	@Test
	public void test_search_by_name_multiple_results() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

		em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("Gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(2, getEmployeeService().search("Gogu").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_search() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

		em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("Gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(2, getEmployeeService().search("Gog").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_case_insensitive() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		getEmployeeService().save(em);

		em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Cucu");
		getEmployeeService().save(em);

		em = new Employee();
		em.setBirthDate(new Date(70, 1, 1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("gogu");
		getEmployeeService().save(em);
		Assert.assertEquals(2, getEmployeeService().search("gOg").size());

	}

}