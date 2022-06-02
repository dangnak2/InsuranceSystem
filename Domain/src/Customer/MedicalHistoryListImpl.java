package Customer;

import DB.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalHistoryListImpl extends DBConnector implements MedicalHistoryList {

  ArrayList<MedicalHistory> medicalHistoryList;

  public MedicalHistoryListImpl() {
    this.medicalHistoryList = new ArrayList<>();
    super.getConnection();
    this.medicalHistoryList = this.getMedicalHistoryList();
  }

  public ArrayList<MedicalHistory> getMedicalHistoryList() {
    String query = "select * from medicalhistory;";
    ResultSet rs = super.retreive(query);
    ArrayList<MedicalHistory> medicalHistories = new ArrayList<MedicalHistory>();

    try {
      while (rs.next()) {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setCustomer_id(rs.getInt("cutomer_id"));
        medicalHistory.setHistoryYear(rs.getInt("historyYear"));
        medicalHistory.setCureComplete(rs.getBoolean("CureComplete"));
        medicalHistory.setMyDisease(
            MedicalHistory.Disease.valueOf(rs.getString("MyDisease")));


        medicalHistories.add(medicalHistory);
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return medicalHistories;
  }


  @Override
  public boolean add(MedicalHistory medicalHistory) {
    String query = "insert into medicalhistory values ("
        + medicalHistory.getCustomer_id() + "," + medicalHistory.getHistoryYear() + ","
        + medicalHistory.isCureComplete() + ",'" + medicalHistory.getMyDisease() + "');";

    if(super.create(query)){
      this.medicalHistoryList = getMedicalHistoryList();
      return true;
    }
    return false;
  }

  @Override
  public MedicalHistory get(int customer_Id) {
    for (MedicalHistory medicalHistory : medicalHistoryList) {
      if (medicalHistory.getCustomer_id() == customer_Id) {
        return medicalHistory;
      }
    }
    return null;
  }

  @Override
  public boolean update(MedicalHistory medicalHistory) {
    String query = "update medicalhistory set"
        + " historyYear = " + medicalHistory.getHistoryYear()
        + ", cureComplete = " + medicalHistory.isCureComplete() + " , MyDisease = '"
        + medicalHistory.getMyDisease() + "' where customer_id = " + medicalHistory.getCustomer_id();
    if (super.update(query)){
      this.medicalHistoryList = getMedicalHistoryList();
      return true;
    }
     return false;
  }

  @Override
  public int getSize() {
    return this.medicalHistoryList.size();
  }


  @Override
  public boolean delete(int customer_id) {

    String query = "delete from medicalhistory where customer_id=" + customer_id;

    if(super.delete(query)) {
      this.medicalHistoryList = getMedicalHistoryList();
      return true;
    }
      return false;
  }
}