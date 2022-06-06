package DAO.InsuranceDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Insurance.FireInsurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBFireInsuranceDAO extends DBConnectorDAO implements FireInsuranceDAO {



  public DBFireInsuranceDAO() {
    this.getConnection();
  }

  public ArrayList<FireInsurance> getFireInsuranceList(){
    ArrayList<FireInsurance> fireInsuranceList = new ArrayList<>();
    String query = "select * from fireInsurance;";
    ResultSet rs = super.retrieve(query);
    try {
      while (rs.next()) {
        FireInsurance fireInsurance = new FireInsurance();

        fireInsurance.setId(rs.getInt("fireInsurance_id"));
        fireInsurance.setHumanDamageBasicMoney(rs.getInt("humanDamageBasicMoney"));
        fireInsurance.setSurroundingDamageBasicMoney(rs.getInt("surroundingDamageBasicMoney"));
        fireInsurance.setBuildingDamageBasicMoney(rs.getInt("buildingDamageBasicMoney"));

        fireInsuranceList.add(fireInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return fireInsuranceList;
  }

  @Override
  public boolean add(FireInsurance fireInsurance) {
      String query = "INSERT INTO fireInsurance VALUES ("
              + fireInsurance.getId() + "," + fireInsurance.getSurroundingDamageBasicMoney()
              + "," + fireInsurance.getHumanDamageBasicMoney() + "," + fireInsurance.getBuildingDamageBasicMoney() + ");";
    if (super.create(query)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM fireInsurance WHERE fireInsurance_id =  " + insuranceId + ";";
    if (super.delete(query)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean update(FireInsurance fireInsurance) {
      String query = "UPDATE fireInsurance SET surroundingDamageBasicMoney = "
              + fireInsurance.getSurroundingDamageBasicMoney()
              + ", humanDamageBasicMoney = " + fireInsurance.getHumanDamageBasicMoney() + ", buildingDamageBasicMoney = " + fireInsurance.getBuildingDamageBasicMoney()
              + " WHERE fireInsurance_id = " + fireInsurance.getId();
     if(super.update(query)){
      return true;
    } else {
      return false;
    }
  }

  @Override
  public FireInsurance get(int insuranceId) {
    for(FireInsurance fireInsurance : getFireInsuranceList()){
      if(insuranceId == fireInsurance.getId()){
        return fireInsurance;
      }
    }
    return null;
  }

  @Override
  public int getSize() {
    return getFireInsuranceList().size();
  }
}
