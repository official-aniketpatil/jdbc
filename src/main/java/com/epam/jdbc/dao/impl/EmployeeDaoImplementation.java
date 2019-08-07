package com.epam.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.jdbc.dao.EmployeeDao;
import com.epam.jdbc.exception.RecordNotFoundException;
import com.epam.jdbc.launcher.DbConnection;
import com.epam.jdbc.model.Address;
import com.epam.jdbc.model.Employee;

public class EmployeeDaoImplementation implements EmployeeDao {
	private Connection connection;
	private static final Logger logger = LogManager.getLogger(EmployeeDaoImplementation.class);

	public EmployeeDaoImplementation() {
		this.connection = new DbConnection().getDbConnection();
	}

	public void add(Employee employee) {
		try (Statement stmt = connection.createStatement();) {
			Address address = employee.getAddress();
			String empInsertionQuery = "insert into employee(emp_id, emp_name, email, level) " + "values("
					+ employee.getId() + ",'" + employee.getName() + "','" + employee.getEmail() + "',"
					+ employee.getLevel() + ")";

			String deptInsertionQuery = "insert into department(emp_id,dept_name) values(" + employee.getId() + ",'"
					+ employee.getDepartment() + "')";
			
			String addrInsertionQuery = "insert into address(emp_id, locality, city, state, landmark, zip) values("
					+ employee.getId() + ",'" + address.getLocality() + "','" + address.getCity() + "','"
					+ address.getState() + "','" + address.getLandmark() + "'," + address.getZip() + ")";
			
			stmt.execute(empInsertionQuery);
			stmt.execute(deptInsertionQuery);
			stmt.execute(addrInsertionQuery);
			logger.trace("employee successfully added");

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}

	}

	public void remove(int id) {

		try (Statement stmt = connection.createStatement();) {
			stmt.execute("delete from employee where emp_id = " + id);
			stmt.execute("delete from department where emp_id = " + id);
			stmt.execute("delete from address where emp_id = " + id);
			logger.trace("employee successfully removed");
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
	}

	public void update(Employee employee) {
		
		String empUpdateQuery  = "update employee set emp_name = '" + employee.getName() + "', email = '" + employee.getEmail()
		+ "', level = " + employee.getLevel() + " where emp_id = " + employee.getId() ;
		
		String deptUpdateQuery = "update department set dept_name = '" + employee.getDepartment() + "'" + " where emp_id = "
				+ employee.getId();
		
		String addrUpdateQuery = "update address set city = '" + employee.getAddress().getCity() + "', locality = '"
				+ employee.getAddress().getLocality() + "', state = '" + employee.getAddress().getState() + "', zip = "
				+ employee.getAddress().getZip()+", landmark = '"+ employee.getAddress().getLandmark() + "' where emp_id = " + employee.getId();
		
		try (Statement stmt = connection.createStatement();) {
			stmt.execute(empUpdateQuery);

			stmt.execute(deptUpdateQuery);

			stmt.execute(addrUpdateQuery);
			
			logger.info("Employee updated successfully" + employee);

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();

		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select emp_id from employee")) {
			while (rs.next()) {
				Employee emp = getById(rs.getInt("emp_id"));
				employees.add(emp);
			}
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
		return employees;
	}

	public Employee getById(int id) {
		Employee emp = new Employee();
		
		String getEmployeeQuery = "SELECT employee.emp_id, employee.emp_name,"
				+ "employee.email,employee.email,employee.level,"
				+ "department.dept_name,address.locality,address.city,"
				+ "address.state,address.state,address.landmark,"
				+ "address.zip from employee JOIN address JOIN department "
				+ "where employee.emp_id = " + id;
			
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(getEmployeeQuery);) {
			while(rs.next()) {
			String name = rs.getString("emp_name");
			int level = rs.getInt("level");
			String department = rs.getString("dept_name");
			String email = rs.getString("email");
			String locality = rs.getString("locality");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String landmark = rs.getString("landmark");
			int zip = rs.getInt("zip");
			Address address = new Address(locality, city, state, landmark, zip);
			emp.setAddress(address);
			emp.setId(id);
			emp.setName(name);
			emp.setDepartment(department);
			emp.setEmail(email);
			emp.setLevel(level);
			}

		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new RecordNotFoundException("Employee you are looking for is not present.");
		}
		return emp;
	}

}
