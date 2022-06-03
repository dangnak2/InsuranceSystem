package Insurance;

import DB.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FireInsuranceListImpl extends DBConnector implements FireInsuranceList {

  private ArrayList<FireInsurance> fireInsuranceList;

  public FireInsuranceListImpl() {
    this.getConnection();
    this.fireInsuranceList = new ArrayList<>();
    this.fireInsuranceList = getFireInsuranceList();
  }

  public ArrayList<FireInsurance> getFireInsuranceList(){
    String query = "select * from fireInsurance;";
    ResultSet rs = super.retreive(query);
    ArrayList<FireInsurance> fires = new ArrayList<FireInsurance>();
    try {
      while (rs.next()) {
        FireInsurance fireInsurance = new FireInsurance();

        fireInsurance.setFireInsurance_id(rs.getInt("fireInsurance_id"));
        fireInsurance.setHumanDamageBasicMoney(rs.getInt("humanDamageBasicMoney"));
        fireInsurance.setSurroundingDamageBasicMoney(rs.getInt("surroundingDamageBasicMoney"));
        fireInsurance.setBuildingDamageBasicMoney(rs.getInt("buildingDamageBasicMoney"));

        fires.add(fireInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return fires;
  }

  @Override
  public boolean add(FireInsurance fireInsurance) {
      String query = "INSERT INTO fireInsurance VALUES ("
              + fireInsurance.getFireInsurance_id() + "," + fireInsurance.getSurroundingDamageBasicMoney()
              + "," + fireInsurance.getHumanDamageBasicMoney() + "," + fireInsurance.getBuildingDamageBasicMoney() + ");";
      if(super.create(query)) {
        this.fireInsuranceList = this.getFireInsuranceList();
        return true;
      }
      return false;
  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM fireInsurance WHERE fireInsurance_id =  " + insuranceId + ";";
      if(super.delete(query)) {
        this.fireInsuranceList = getFireInsuranceList();
        return true;
      }
    return false;
  }

  @Override
  public boolean update(FireInsurance fireInsurance) {
    if (fireInsuranceList.contains(fireInsurance)) {
      String query = "UPDATE fireInsurance SET ("
              + fireInsurance.getFireInsurance_id() + "," + fireInsurance.getSurroundingDamageBasicMoney()
              + "," + fireInsurance.getHumanDamageBasicMoney() + "," + fireInsurance.getBuildingDamageBasicMoney()
              + ")" + "WHERE fireInsurance_id = " + fireInsurance.getFireInsurance_id();
      super.update(query);
      this.fireInsuranceList = this.getFireInsuranceList();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public FireInsurance get(int insuranceId) {
    for(FireInsurance fireInsurance : fireInsuranceList){
      if(insuranceId == fireInsurance.getId()){
        return fireInsurance;
      }
    }
    return null;
  }

  @Override
  public int getSize() {
    return this.fireInsuranceList.size();
  }
}
