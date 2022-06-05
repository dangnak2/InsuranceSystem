package DAO.InsuranceDAO;

import Domain.Insurance.CarInsurance;

public interface CarInsuranceDAO {

  public boolean add(CarInsurance carInsurance);

  public boolean delete(int insuranceId);

  public boolean update(CarInsurance insuranceId);

  public CarInsurance get(int insuranceId);

  public int getSize();
}
