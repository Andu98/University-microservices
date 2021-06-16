package com.tudor.request;

public class CreateStudentRequest {

	private String firstName;

	private String lastName;

	private String email;

	//addressId nu este creat din student-service
	//Acesta este creat in aplicatia address-service 
	//iar id-ul adresei este oferit studentului din micro-serviciul student-service
	private long addressId;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

}
