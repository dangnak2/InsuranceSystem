package DAO.InsuranceDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Insurance.SeaInsurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBSeaInsuranceDAO extends DBConnectorDAO implements SeaInsuranceDAO {


  public DBSeaInsuranceDAO() {
    this.getConnection();
  }

  public ArrayList<SeaInsurance> getSeaInsuranceList() {
    ArrayList<SeaInsurance> seaInsuranceList = new ArrayList<>();
    String query = "select * from seaInsurance;";
    ResultSet rs = super.retrieve(query);
    try {
      while (rs.next()) {
        SeaInsurance seaInsurance = new SeaInsurance();

        seaInsurance.setId(rs.getInt("seaInsurance_id"));
        seaInsurance.setGeneralDamageBasicMoney(rs.getInt("generalDamageBasicMoney"));
        seaInsurance.setRevenueDamageBasicMoney(rs.getInt("revenueDamageBasicMoney"));

        seaInsuranceList.add(seaInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return seaInsuranceList;
  }

  @Override
  public boolean add(SeaInsurance seaInsurance) {
      String query = "INSERT INTO seaInsurance VALUES ("
              + seaInsurance.getId() + ","
              + seaInsurance.getGeneralDamageBasicMoney() + "," + seaInsurance.getRevenueDamageBasicMoney() + ");";

    if (super.create(query)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM seaInsurance WHERE seaInsurance_id =  " + insuranceId + ";";
    if (super.delete(query)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public SeaInsurance get(int insuranceId) {
    for(SeaInsurance seaInsurance : getSeaInsuranceList()){
      if(insuranceId == seaInsurance.getId()){
        return seaInsurance;
      }
    }
    return null;
  }

  @Override
  public boolean update(SeaInsurance seaInsurance) {
    String query = "UPDATE seaInsurance SET generalDamageBasicMoney = "
            + seaInsurance.getGeneralDamageBasicMoney()
            + ", revenueDamageBasicMoney = " + seaInsurance.getRevenueDamageBasicMoney()
            + " WHERE seaInsurance_id = " + seaInsurance.getId();
      if(super.update(query)){
      return true;
    } else {
      return false;
    }
  }


  @Override
  public int getSize() {
    return getSeaInsuranceList().size();
  }

}
