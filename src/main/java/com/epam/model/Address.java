package com.epam.model;

public class Address {
	private String locality;
	private String city;
	private String state;
	private String landmark;
	private int zip;
	
	public Address() {
		
	}
	
	public Address(String locality, String city, String state, String landmark, int zip) {
		super();
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.zip = zip;
	}

	public String getLocality() {
		return locality;
	}

	@Override
	public String toString() {
		return "Address [locality=" + locality + ", city=" + city + ", state=" + state + ", landmark=" + landmark
				+ ", zip=" + zip + "]";
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
}
