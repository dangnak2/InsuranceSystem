package Staff;


import java.util.Date;

import Auth.Login;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public class Staff {

	public enum Department {
		Design,
		Sales,
		CompensationManagement,
		;
	};
	private String email;
	private boolean gender;
	private int id;
	private Date joinDate;
	private String name;
	private String phoneNum;
	private enum position{};
	private int salary;
	private String SSN;
	private String password;
	private Department department;


	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getSSN() {
		return SSN;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSSN(String ssn) {
		// TODO Auto-generated method stub
		this.SSN = ssn;
	}

	public Department getDepartment() {

		return department;
	}



}
