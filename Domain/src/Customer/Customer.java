//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

import java.util.Date;

public class Customer {
	private int id;
	private int age;
	private String account;
	private String address;
	private String email;
	private Customer.Job job;
	private MedicalHistory medicalHistory;
	private String name;
	private String phoneNumber;
	private boolean sex;
	private String SSN;
	private boolean pay;
	private Date joinDate;
	private House house;
	private Car car;
	private Ship ship;

	public Customer() {
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Customer.Job getJob() {
		return this.job;
	}

	public void setJob(Customer.Job job) {
		this.job = job;
	}

	public boolean isPay() {
		return this.pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MedicalHistory getMedicalHistory() {
		return this.medicalHistory;
	}

	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isSex() {
		return this.sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getSSN() {
		return this.SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Ship getShip() {
		return this.ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public static enum Job {
		영업직(70, false),
		생산직(60, true),
		사무직(75, false),
		자영업자(70, true),
		무직(30, false);

		private int maxAge;
		private boolean danger;

		private Job(int maxAge, boolean danger) {
			this.maxAge = maxAge;
			this.danger = danger;
		}

		public int getMaxAge() {
			return this.maxAge;
		}

		public boolean isDanger() {
			return this.danger;
		}
	}
}
