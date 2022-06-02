package Insurance;


import Customer.Car;
import DB.DBConnector;

import java.sql.*;
import java.util.ArrayList;


public class InsuranceListImpl extends DBConnector implements InsuranceList {

  private ArrayList<Insurance> insuranceList;

  public InsuranceListImpl() {
    this.insuranceList = new ArrayList<>();
  }

  public ArrayList<Insurance> getInsuranceList() {
    this.getFireInsuranceList();
    this.getCarInsuranceList();
    this.getSeaInsuranceList();

    return insuranceList;
  }

  @Override
  public boolean add(Insurance insurance) {
      String query = "INSERT INTO insurance VALUES ("
              + insurance.getId() + "," + insurance.isAuthorization() + ",'" + insurance.getAuthorizationDate()
              + "','" + insurance.getCreatedDate() + "','" + insurance.getExplanation() + "','" + insurance.getModifiedDate() + "','"
              + insurance.getName() + "'," + insurance.getPremium() + ",'" + insurance.getType() + "'" +  ");";
//      super.add(query);
    this.insuranceList = getInsuranceList();
    return true;
  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM insurance WHERE id =  " + insuranceId + ";";
      super.delete(query);
      this.insuranceList = this.getInsuranceList();
      return true;
  }

  @Override
  public Insurance get(int insuranceId) {
    for (Insurance insurance : insuranceList) {
      if (insuranceId == insurance.getId()) {
        return insurance;
      }
    }
    return null;
  }

  @Override
  public int getSize() {
    return this.insuranceList.size();
  }

  private void getFireInsuranceList() {
    String query = "select * from Insurance where type = 'fire';";
    ResultSet rs = super.retreive(query);
    try {
      while (rs.next()) {
        FireInsurance fireInsurance = new FireInsurance();

        fireInsurance.setId(rs.getInt("id"));
        fireInsurance.setAuthorization(rs.getBoolean("authorization"));
        fireInsurance.setAuthorizationDate(rs.getDate("authorizedDate"));
        fireInsurance.setCreatedDate(rs.getDate("createdDate"));
        fireInsurance.setExplanation(rs.getString("explanation"));
        fireInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        fireInsurance.setPremium(rs.getInt("premium"));
        fireInsurance.setName(rs.getString("name"));

        this.insuranceList.add(fireInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  private void getCarInsuranceList() {
    String query = "select * from Insurance where type = 'car';";
    ResultSet rs = super.retreive(query);
    try {
      while (rs.next()) {
        CarInsurance carInsurance = new CarInsurance();

        carInsurance.setId(rs.getInt("id"));
        carInsurance.setAuthorization(rs.getBoolean("authorization"));
        carInsurance.setAuthorizationDate(rs.getDate("authorizedDate"));
        carInsurance.setCreatedDate(rs.getDate("createdDate"));
        carInsurance.setExplanation(rs.getString("explanation"));
        carInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        carInsurance.setPremium(rs.getInt("premium"));
        carInsurance.setName(rs.getString("name"));

        this.insuranceList.add(carInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  private void getSeaInsuranceList() {
    String query = "select * from Insurance where type = 'sea';";
    ResultSet rs = super.retreive(query);
    try {
      while (rs.next()) {
        SeaInsurance seaInsurance = new SeaInsurance();

        seaInsurance.setId(rs.getInt("id"));
        seaInsurance.setAuthorization(rs.getBoolean("authorization"));
        seaInsurance.setAuthorizationDate(rs.getDate("authorizedDate"));
        seaInsurance.setCreatedDate(rs.getDate("createdDate"));
        seaInsurance.setExplanation(rs.getString("explanation"));
        seaInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        seaInsurance.setPremium(rs.getInt("premium"));
        seaInsurance.setName(rs.getString("name"));

        this.insuranceList.add(seaInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
