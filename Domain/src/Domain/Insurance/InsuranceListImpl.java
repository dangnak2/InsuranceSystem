package Domain.Insurance;


import DAO.DBConnector;

import java.sql.*;
import java.util.ArrayList;


public class InsuranceListImpl extends DBConnector implements InsuranceList {

  private ArrayList<Insurance> insuranceList;

  public InsuranceListImpl() {
    this.getConnection();
    this.insuranceList = new ArrayList<>();
    this.insuranceList = getInsuranceList();
  }          

  public ArrayList<Insurance> getInsuranceList() {
    ArrayList<Insurance> insuranceList = new ArrayList<>();

    ArrayList<Insurance> fireInsurances = this.getFireInsuranceList();
    ArrayList<Insurance> carInsurances = this.getCarInsuranceList();
    ArrayList<Insurance> seaInsurances = this.getSeaInsuranceList();

    insuranceList.addAll(fireInsurances);
    insuranceList.addAll(carInsurances);
    insuranceList.addAll(seaInsurances);

    this.insuranceList = insuranceList;
    return insuranceList;
  }

  @Override
  public boolean add(Insurance insurance) {
      String query = "INSERT INTO insurance VALUES ("
              + insurance.getId() + "," + insurance.isAuthorization() + "," + null
              + ",'" + insurance.getCreatedDate() + "','" + insurance.getExplanation() + "','" + insurance.getModifiedDate() + "','"
              + insurance.getName() + "'," + insurance.getPremium() + ",'" + insurance.getType() + "'" +  ");";
      super.create(query);
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
  public boolean update(Insurance insurance) {
    if (insuranceList.contains(insurance)) {
      String query = "UPDATE insurance set authorization = "
              + insurance.isAuthorization() + ", authorizedDate = '" + insurance.getAuthorizedDate()
              + "', createdDate = '" + insurance.getCreatedDate() + "', explanation = '" + insurance.getExplanation()
              + "', modifiedDate = '" + insurance.getModifiedDate() + "', name = '"
              + insurance.getName() + "', premium = " + insurance.getPremium() + ", type = '" + insurance.getType()
          + "' where id = " + insurance.getId();
      super.update(query);
      this.insuranceList = getInsuranceList();
      return true;
    } else {
      return false;
    }
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

  private ArrayList<Insurance> getFireInsuranceList() {
    String query = "select * from insurance where type = 'Fire';";
    ResultSet rs = super.retreive(query);
    ArrayList<Insurance> fireInsurances = new ArrayList<>();
    try {
      while (rs.next()) {
        FireInsurance fireInsurance = new FireInsurance();

        fireInsurance.setId(rs.getInt("id"));
        fireInsurance.setAuthorization(rs.getBoolean("authorization"));
        fireInsurance.setAuthorizedDate(rs.getDate("authorizedDate"));
        fireInsurance.setCreatedDate(rs.getDate("createdDate"));
        fireInsurance.setExplanation(rs.getString("explanation"));
        fireInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        fireInsurance.setPremium(rs.getInt("premium"));
        fireInsurance.setName(rs.getString("name"));
        fireInsurance.setType(Insurance.Type.valueOf(rs.getString("type")));

        fireInsurances.add(fireInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return fireInsurances;
  }
  private ArrayList<Insurance> getCarInsuranceList() {
    String query = "select * from insurance where type = 'Car';";
    ResultSet rs = super.retreive(query);
    ArrayList<Insurance> carInsurances = new ArrayList<Insurance>();
    try {
      while (rs.next()) {
        CarInsurance carInsurance = new CarInsurance();

        carInsurance.setId(rs.getInt("id"));
        carInsurance.setAuthorization(rs.getBoolean("authorization"));
        carInsurance.setAuthorizedDate(rs.getDate("authorizedDate"));
        carInsurance.setCreatedDate(rs.getDate("createdDate"));
        carInsurance.setExplanation(rs.getString("explanation"));
        carInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        carInsurance.setPremium(rs.getInt("premium"));
        carInsurance.setName(rs.getString("name"));
        carInsurance.setType(Insurance.Type.valueOf(rs.getString("type")));

        carInsurances.add(carInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return carInsurances;
  }
  private ArrayList<Insurance> getSeaInsuranceList() {
    String query = "select * from insurance where type = 'Sea';";
    ResultSet rs = super.retreive(query);
    ArrayList<Insurance> seaInsurances = new ArrayList<Insurance>();
    try {
      while (rs.next()) {
        SeaInsurance seaInsurance = new SeaInsurance();

        seaInsurance.setId(rs.getInt("id"));
        seaInsurance.setAuthorization(rs.getBoolean("authorization"));
        seaInsurance.setAuthorizedDate(rs.getDate("authorizedDate"));
        seaInsurance.setCreatedDate(rs.getDate("createdDate"));
        seaInsurance.setExplanation(rs.getString("explanation"));
        seaInsurance.setModifiedDate(rs.getDate("modifiedDate"));
        seaInsurance.setPremium(rs.getInt("premium"));
        seaInsurance.setName(rs.getString("name"));
        seaInsurance.setType(Insurance.Type.valueOf(rs.getString("type")));

        seaInsurances.add(seaInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return seaInsurances;
  }
}
