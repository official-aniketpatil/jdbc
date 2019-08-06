package com.epam.jdbc_example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.exception.RecordNotFoundException;
import com.epam.model.Address;
import com.epam.model.Employee;

public class EmployeeDao {
	private Connection connection;
	private static final Logger logger = LogManager.getLogger(EmployeeDao.class); 
	
	public EmployeeDao() {
		this.connection = new DbConnection().getDbConnection();
	}
	public void add(Employee employee) {
		try(Statement stmt = connection.createStatement();){
			Address address = employee.getAddress();
		   stmt.execute("insert into employee(emp_id, emp_name, email, level) values("+employee.getId()+","+employee.getName()+","
		   		+ ""+employee.getEmail()+","+employee.getLevel()+")");
		   stmt.execute("insert into department(emp_id,dept_name) values("+employee.getId()+","+employee.getDepartment()+")");
		   
		   stmt.execute("insert into address(emp_id, locality, city, state, landmark, zip) values("+employee.getId()+
				   "," + address.getLocality()+
				   "," + address.getCity()+
				   "," + address.getState()+
				   "," + address.getLandmark()+
				   "," + address.getZip()+
				   "");
		   
		} catch(SQLException sqle) {
			logger.error(sqle.getMessage());
		}
		
	}
	
	public void remove(int id) {
		
		try (Statement stmt = connection.createStatement();){
			stmt.execute("delete from employee where emp_id = "+id);
			stmt.execute("delete from department where emp_id = "+id);
			stmt.execute("delete from address where emp_id = " + id);
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
	}
	
	public void update(Employee employee) {
		try (Statement stmt = connection.createStatement();){
			stmt.execute("update employee set emp_name = "+employee.getName()+
					", email = " + employee.getEmail()+
					", level = " + employee.getLevel()+
					"where id = " + employee.getId()+"");
			
			stmt.execute("update department set dept_name = "+employee.getDepartment()+""
					+ "where emp_id = " + employee.getDepartment());
			
			stmt.execute("update address set city = "+employee.getAddress().getCity()+
					", locality = " + employee.getAddress().getLocality()+
					", state = " + employee.getAddress().getState()+
					", zip = " + employee.getAddress().getZip());
			logger.trace("Employee updated successfully" + employee);
			
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		
		try(Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select emp_id from employee")
		){
			while(rs.next()) {
				Employee emp = get(rs.getInt("emp_id"));
				employees.add(emp);
			}
		} catch(SQLException sqle) {
			logger.error(sqle.getMessage());
		}
		return employees;
	}
	public Employee get(int id) {
		Employee emp = new Employee();
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select from employee natural join address "
						+ "natural join department"+
						"where id = " + id);
			){
			String name = rs.getString("emp_name");
			int level = rs.getInt("level");
			String department = rs.getString("dept_name");
			String email = rs.getString("email");
			String locality = rs.getString("locality");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String landmark =  rs.getString("landmark");
			int zip = rs.getInt("zip");
			Address address = new Address(locality, city, state, landmark, zip); 
			emp.setAddress(address);
			emp.setId(id);
			emp.setName(name);
			emp.setDepartment(department);
			emp.setEmail(email);
			emp.setLevel(level);
			 
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
			throw new RecordNotFoundException("Employee you are looking for is not present.");
		}
		return emp;
	}
	
}
