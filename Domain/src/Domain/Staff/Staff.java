package Domain.Staff;


import java.util.Date;

public class Staff {

  public enum Department {
    Design,
    Underwriting,
    Sales,
    Human,
    CompensationManagement
  }

  public enum Position {
    Staff(1500000), //사원
    Supervisor(2000000),
    AssistantManager(2500000),
    Manager(3000000),
    DeputyGeneralManager(3500000),
    GeneralManager(4000000);

    private int salary;
    Position(int salary) {
      this.salary = salary;
    }

    public int getSalary() {
      return salary;
    }
  }


  private String email;
  private boolean gender;
  private int id;
  private Date joinDate;
  private String name;
  private String phoneNum;
  private String SSN;
  private String password;
  private Department department;
  private Position position;

  private int basicSalary;
  private int totalSalary;
  private int result; //실적


  public Staff() {
    if (this.position != null) {
      this.basicSalary = this.position.getSalary();
    }
  }


  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
    this.setBasicSalary(position.getSalary());
  }

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
    this.SSN = ssn;
  }

  public int getBasicSalary() {
    return basicSalary;
  }

  public void setBasicSalary(int basicSalary) {
    if (this.position != null) {
      this.basicSalary = this.position.getSalary();
    }
  }

  public int getTotalSalary() {
    return totalSalary;
  }

  public void setTotalSalary(int totalSalary) {
    this.totalSalary = totalSalary;
  }

  public Department getDepartment() {
    return department;
  }


}
