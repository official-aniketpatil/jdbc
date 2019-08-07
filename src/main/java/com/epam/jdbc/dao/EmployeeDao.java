package com.epam.jdbc.dao;

import java.util.List;
import com.epam.jdbc.model.Employee;

public interface EmployeeDao {
	public void add(Employee employee);
	public void remove(int id);
	public void update(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee  getById(int id);
}
