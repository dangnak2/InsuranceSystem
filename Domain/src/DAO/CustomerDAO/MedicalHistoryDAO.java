package DAO.CustomerDAO;

import Domain.Customer.MedicalHistory;

import java.util.ArrayList;

public interface MedicalHistoryDAO {
  boolean add(MedicalHistory medicalHistory);

  boolean delete(int customerId);

  MedicalHistory get(int customerId);

  boolean update(MedicalHistory medicalHistory);

  int getSize();

  public ArrayList<MedicalHistory> getMedicalHistoryList();

}
