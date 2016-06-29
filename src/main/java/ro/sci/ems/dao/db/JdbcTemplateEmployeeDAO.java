package ro.sci.ems.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ro.sci.ems.dao.EmployeeDAO;
import ro.sci.ems.domain.Employee;
import ro.sci.ems.domain.Gender;

public class JdbcTemplateEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplateEmployeeDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<Employee> getAll() {
		return jdbcTemplate.query("select * from employee", new EmployeeMapper());
	}

	@Override
	public Employee findById(Long id) {
		return jdbcTemplate.queryForObject("select * from employee where id = ?",
				new Long[] { id },
				new EmployeeMapper());
	}

	@Override
	public Employee update(Employee model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Employee model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Employee> searchByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class EmployeeMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getLong("id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setJobTitle(rs.getString("job_title"));
			employee.setBirthDate(new Date(rs.getTimestamp("birth_date").getTime()));
			employee.setEmploymentDate(new Date(rs.getTimestamp("employment_date").getTime()));
			employee.setGender(Gender.valueOf(rs.getString("gender")));
			return employee;
		}

	}

}
