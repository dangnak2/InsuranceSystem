package Domain.Insurance;

import DAO.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeaInsuranceListImpl extends DBConnector implements SeaInsuranceList {

  private ArrayList<SeaInsurance> seaInsuranceList;

  public SeaInsuranceListImpl() {
    this.getConnection();
    this.seaInsuranceList = new ArrayList<>();
  }

  public ArrayList<SeaInsurance> getSeaInsuranceList() {
    String query = "select * from seaInsurance;";
    ResultSet rs = super.retreive(query);
    try {
      while (rs.next()) {
        SeaInsurance seaInsurance = new SeaInsurance();

        seaInsurance.setSeeInsurance_id(rs.getInt("seaInsurance_id"));
        seaInsurance.setGeneralDamageBasicMoney(rs.getInt("generalDamageBasicMoney"));
        seaInsurance.setRevenueDamageBasicMoney(rs.getInt("revenueDamageBasicMoney"));

        this.seaInsuranceList.add(seaInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return seaInsuranceList;
  }

  @Override
  public boolean add(SeaInsurance seaInsurance) {
      String query = "INSERT INTO seaInsurance VALUES ("
              + seaInsurance.getSeeInsurance_id() + ","
              + seaInsurance.getGeneralDamageBasicMoney() + "," + seaInsurance.getRevenueDamageBasicMoney() + ");";
//      super.add(query);
      this.seaInsuranceList = this.getSeaInsuranceList();
      return true;
  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM seaInsurance WHERE seaInsurance_id =  " + insuranceId + ";";
      super.delete(query);
      this.seaInsuranceList = this.getSeaInsuranceList();
      return true;
  }

  @Override
  public SeaInsurance get(int insuranceId) {
    for(SeaInsurance seaInsurance : seaInsuranceList){
      if(insuranceId == seaInsurance.getId()){
        return seaInsurance;
      }
    }
    return null;
  }

  @Override
  public boolean update(SeaInsurance seaInsurance) {
    if (seaInsuranceList.contains(seaInsurance)) {
      String query = "UPDATE carInsurance SET ("
              + seaInsurance.getSeeInsurance_id() + "," + seaInsurance.getGeneralDamageBasicMoney()
              + "," + seaInsurance.getRevenueDamageBasicMoney() + ")" + "WHERE carInsurance_id = " + seaInsurance.getSeeInsurance_id();
      super.update(query);
      this.seaInsuranceList = this.getSeaInsuranceList();
      return true;
    } else {
      return false;
    }
  }


  @Override
  public int getSize() {
    return 0;
  }
}
