package com.projectpintas;

/*Create the Student class. This class is our model and 
contains the data we will save in the database and show in the user interface.*/

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	 
	public Student(){}
	 
	public Student(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	 
	//Get and Set methods

	public void setId(int id0) {
		this.id = id0;
	}
		
	public int getId() {
		return this.id;
	}
		
	public void setFirstName(String first0) {
		this.firstName = first0;		
	}
		
	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String last0) {
		this.lastName = last0;
	}
		
	public String getLastName() {
		return this.lastName;
	}
		
	@Override
	public String toString() {
		return "Student id: " + id + "\t Student Name:\t" + firstName + "\t" + lastName;
	}
}