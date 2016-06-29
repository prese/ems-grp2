package ro.sci.ems.dao;

import java.util.Collection;

import ro.sci.ems.domain.Employee;

public interface EmployeeDAO extends BaseDAO<Employee>{

	Collection<Employee> searchByName(String query);
}
