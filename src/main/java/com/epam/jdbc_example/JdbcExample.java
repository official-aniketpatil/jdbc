package com.epam.jdbc_example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.exception.DbConnectionFailedException;
import com.epam.model.Address;
import com.epam.model.Employee;

public class JdbcExample {

	private static final Logger logger = LogManager.getLogger(JdbcExample.class);
	Scanner sc = new Scanner(System.in);
	private EmployeeDao employeeDao;
	
	public JdbcExample(EmployeeDao employeeDao){
		this.employeeDao = employeeDao;
	}
	public int getChoice() {
		logger.trace("Enter\n1) add new employee");
		logger.trace("2) update existing employee");
		logger.trace("3) delete employee");
		logger.trace("4) show employee details");
		logger.trace("5) exit");
		
		return sc.nextInt();
	}
	private void showMenu() {
		logger.trace("Enter Employee dedtails in sequence");
		logger.trace("* employee id");
		logger.trace("* name of employee");
		logger.trace("* level");
		logger.trace("* email");
		logger.trace("* Department");
		logger.trace("* locality");
		logger.trace("* city");
		logger.trace("* state");
		logger.trace("* landmark");
		logger.trace("* zip");

	}
	public void addEmployee() {
		Employee emp = new Employee();
		showMenu();
		setEmployeeDetailsFromInput(emp);
		employeeDao.add(emp);
	}
	
	public void updateEmployee(int id) {

		Employee emp = employeeDao.get(id);
		logger.trace("existing employee is:" + emp);
		showMenu();
		setEmployeeDetailsFromInput(emp);
		employeeDao.update(emp);
	}
	private void setEmployeeDetailsFromInput(Employee emp) {
		Address address = new Address();
		
		emp.setId(sc.nextInt());
		emp.setName(sc.next().trim());
		emp.setLevel(sc.nextInt());
		emp.setEmail(sc.next().trim());
		emp.setDepartment(sc.next().trim());
		address.setLocality(sc.next().trim());
		address.setCity(sc.next().trim());
		address.setState(sc.next().trim());
		address.setLandmark(sc.next().trim());
		address.setZip(sc.nextInt());
		
		emp.setAddress(address);
	}
	public void deleteEmployee(int id) {
		employeeDao.remove(id);
	}
	
	public void showEmployeeDetails() {
		List<Employee> employees = employeeDao.getAllEmployees();
		for(Employee emp: employees) {
			logger.trace(emp);
		}
	}
	

	public void helper() {
		int choice = 0;
		
		while((choice = getChoice()) != 5) {
			switch(choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				logger.trace("Enter id of employee to edit");
				updateEmployee(sc.nextInt());
				break;
			case 3:
				logger.trace("Enter id of employee to delete");
				deleteEmployee(sc.nextInt());
				break;
			case 4:
				showEmployeeDetails();
				break;
			default:
				logger.info("User entered invalid choice");
			}
		}

	}

	public static void main(String[] args) {
			JdbcExample jdbcExample = new JdbcExample(new EmployeeDao());
			jdbcExample.helper();
	}

}
