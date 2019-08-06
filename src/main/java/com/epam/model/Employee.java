package com.epam.model;

public class Employee {
	private int id;
	private String name;
	private String email;
	private int level;
	private Address address;
	private String department;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, String email, int level, Address address, String department) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.level = level;
		this.address = address;
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", level=" + level + ", address="
				+ address + ", department=" + department + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
