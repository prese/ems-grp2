package ro.sci.ems.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		return jdbcTemplate.queryForObject("select * from employee where id = ?", new Long[] { id },
				new EmployeeMapper());
	}

	@Override
	public Employee update(Employee model) {

		String sql = "";
		Long newId = null;
		if (model.getId() > 0) {
			sql = "update employee set first_name=?, last_name=?, job_title=?, birth_date=?, employment_date=?, gender = ? "
					+ "where id = ? returning id";
			newId = jdbcTemplate.queryForObject(sql, new Object[]{
					model.getFirstName(),
					model.getLastName(),
					model.getJobTitle(),
					new Timestamp(model.getBirthDate().getTime()),
					new Timestamp(model.getEmploymentDate().getTime()),
					model.getGender().name(),
					model.getId()
							
			}, new RowMapper<Long>() {
				public Long mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getLong(1);
				}
			});
		} else {
			sql = "insert into employee (first_name, last_name, job_title, birth_date, employment_date, gender) "
					+ "values (?, ?, ?, ?, ?, ?) returning id";
			
			newId = jdbcTemplate.queryForObject(sql, new Object[]{
					model.getFirstName(),
					model.getLastName(),
					model.getJobTitle(),
					new Timestamp(model.getBirthDate().getTime()),
					new Timestamp(model.getEmploymentDate().getTime()),
					model.getGender().name()
							
			}, new RowMapper<Long>() {
				public Long mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getLong(1);
				}
			});
		}
		model.setId(newId);
	
		return model;
	}

	@Override
	public boolean delete(Employee model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Employee> searchByName(String query) {
		return jdbcTemplate.query("select * from employee "
						+ "where lower(first_name || ' ' || last_name) like '%?%'", 
						new String[]{query.toLowerCase()}, 
						new EmployeeMapper());
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
