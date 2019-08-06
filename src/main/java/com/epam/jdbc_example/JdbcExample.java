package com.epam.jdbc_example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public int getChoice() {
		System.out.println("Enter\n1) add new employee");
		System.out.println("2) update existing employee");
		System.out.println("3) delete employee");
		System.out.println("4) show employee details");
		System.out.println("5) exit");
		
		return sc.nextInt();
	}
	
	public void addEmployee() {
		Employee emp = new Employee();
		System.out.println("Enter employee id");
		emp.setId(sc.nextInt());
		System.out.println("Enter name of employee");
		emp.setName(sc.next().trim());
		System.out.println("Enter level");
		emp.setLevel(sc.nextInt());
		System.out.println("Enter email");
		emp.setEmail(sc.next().trim());
		System.out.println("Enter Department");
		emp.setDepartment(sc.next().trim());
		Address address = new Address();
		System.out.println("Enter locality");
		address.setLocality(sc.next().trim());
		System.out.println("Enter city");
		address.setCity(sc.next().trim());
		System.out.println("Enter state");
		address.setState(sc.next().trim());
		System.out.println("Enter landmark");
		address.setLandmark(sc.next().trim());
		System.out.println("Enter zip");
		address.setZip(sc.nextInt());
		
		emp.setAddress(address);
		employeeDao.add(emp);
	}
	
	public void updateEmployee(int id) {
		Employee emp = new Employee();
		System.out.println("Enter employee id");
		emp.setId(sc.nextInt());
		System.out.println("Enter name of employee");
		emp.setName(sc.next().trim());
		System.out.println("Enter level");
		emp.setLevel(sc.nextInt());
		System.out.println("Enter email");
		emp.setEmail(sc.next().trim());
		System.out.println("Enter Department");
		emp.setDepartment(sc.next().trim());
		Address address = new Address();
		System.out.println("Enter locality");
		address.setLocality(sc.next().trim());
		System.out.println("Enter city");
		address.setCity(sc.next().trim());
		System.out.println("Enter state");
		address.setState(sc.next().trim());
		System.out.println("Enter landmark");
		address.setLandmark(sc.next().trim());
		System.out.println("Enter zip");
		address.setZip(sc.nextInt());
		
		emp.setAddress(address);
	}
	
	public void deleteEmployee(int id) {
		employeeDao.remove(id);
	}
	
	public void showEmployeeDetails() {
		
	}
	public void createDbSchema() {
		createTable("create table employee(id int primary key, name varchar(20), email varchar(30),level int)");
		createTable("create table address(emp_id int, locality varchar(20), city varchar(20), state varchar(20)), landmark varchar(40), zip int");
		createTable("create table department(emp_id int, dept_id int primary key, dept_name varchar(20)) ");
	}
	
	public void createTable(String query) {
		try (Connection connection = new DbConnection().getDbConnection();
				Statement stmt = connection.createStatement();) {
			stmt.execute("use epam");
			stmt.execute(query);
			logger.info("table is created");
			
		} catch (DbConnectionFailedException ex) {
			
			logger.error(ex.getMessage());
		
		} catch (SQLException sqle) {
			
			logger.error(sqle.getMessage());
			
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
				updateEmployee();
				break;
			case 3:
				deleteEmployee();
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
			
	}

}
