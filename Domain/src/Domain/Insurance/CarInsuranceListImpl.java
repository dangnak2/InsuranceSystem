package Domain.Insurance;

import DAO.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarInsuranceListImpl extends DBConnector implements CarInsuranceList {

  private ArrayList<CarInsurance> carInsuranceList;

  public CarInsuranceListImpl() {
    this.getConnection();
    this.carInsuranceList = new ArrayList<>();
  }

  public ArrayList<CarInsurance> getCarInsuranceList() {
    String query = "select * from carInsurance;";
    ResultSet rs = super.retreive(query);
    try {
      while (rs.next()) {
        CarInsurance carInsurance = new CarInsurance();

        carInsurance.setCarInsurance_id(rs.getInt("carInsurance_id"));
        carInsurance.setHumanDamageBasicMoney(rs.getInt("humanDamageBasicMoney"));
        carInsurance.setCarDamageBasicMoney(rs.getInt("carDamageBasicMoney"));

        this.carInsuranceList.add(carInsurance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return carInsuranceList;
  }

  @Override
  public boolean add(CarInsurance carInsurance) {
      String query = "INSERT INTO carInsurance VALUES ("
              + carInsurance.getCarInsurance_id() + "," + carInsurance.getHumanDamageBasicMoney()
              + "," + carInsurance.getCarDamageBasicMoney() + ");";
      super.create(query);
      this.carInsuranceList = this.getCarInsuranceList();
      return true;

  }

  @Override
  public boolean delete(int insuranceId) {
      String query = "DELETE FROM carInsurance WHERE carInsurance_id =  " + insuranceId + ";";
      super.delete(query);
      this.carInsuranceList = this.getCarInsuranceList();
      return true;
  }

  @Override
  public boolean update(CarInsurance carInsurance) {
    if (carInsuranceList.contains(carInsurance)) {
      String query = "UPDATE carInsurance SET ("
              + carInsurance.getCarInsurance_id() + "," + carInsurance.getHumanDamageBasicMoney()
              + "," + carInsurance.getCarDamageBasicMoney() + ")" + "WHERE carInsurance_id = " + carInsurance.getCarInsurance_id();
      super.update(query);
      this.carInsuranceList = getCarInsuranceList();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public CarInsurance get(int insuranceId) {
    for(CarInsurance carInsurance : carInsuranceList){
      if(insuranceId == carInsurance.getId()){
        return carInsurance;
      }
    }
    return null;
  }

  @Override
  public int getSize() {
    return 0;
  }
}
