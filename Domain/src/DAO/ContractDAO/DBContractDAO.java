package DAO.ContractDAO;


import DAO.DBConnector.DBConnectorDAO;
import Domain.Contract.Contract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBContractDAO extends DBConnectorDAO implements ContractDAO {

  private ArrayList<Contract> contractList;

  public DBContractDAO() {
    this.contractList = new ArrayList<>();
    super.getConnection();
    this.contractList = this.getContractList();
  }

  @Override
  public ArrayList<Contract> getContractList() {
    String query = "select * from contract;";
    ResultSet rs = super.retrieve(query);
    ArrayList<Contract> contractList = new ArrayList<Contract>();
    try {
      while (rs.next()) {
        Contract contract = new Contract();
        contract.setContractId(rs.getInt("contract_id"));
        contract.setCustomerId(rs.getInt("customer_id"));
        contract.setSalesId(rs.getInt("sales_id"));
        contract.setInsuranceId(rs.getInt("insurance_id"));
        contract.setInsurancePrice(rs.getInt("insurance_price"));
        contract.setPremiumRate(rs.getInt("premium_rate"));
        contract.setCompensationAmount(rs.getDouble("compensation_amount"));
        contract.setContractDate(rs.getDate("contracted_date"));
        contract.setUnderWrite(rs.getBoolean("underwrite"));
        contract.setPay(rs.getBoolean("pay"));


        contractList.add(contract);
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return contractList;
  }

  @Override
  public boolean add(Contract contract) {
    String query = "insert into contract values("
            + contract.getContractId() + "," + contract.getCustomerId() + "," + contract.getSalesId()
            + ","
            + contract.getInsuranceId() + "," + contract.getInsurancePrice() + ","
            + contract.getPremiumRate() + ","
            + contract.getCompensationAmount() + ",'" + contract.getContractDate()
            + "'," + false + "," + contract.isPay() + ");";
    if (super.create(query)) {
      this.contractList = getContractList();
      return true;
    } else {
      return false;
    }
  }


  @Override
  public Contract get(int contractId) {
    for (Contract contract : contractList) {
      if (contractId == contract.getContractId()) {
        return contract;
      }
    }
    return null;
  }


  @Override
  public boolean update(Contract contract) {
    String query = "update contract set "
            + "customer_id = " + contract.getCustomerId() + ", sales_id = " + contract.getSalesId()
            + ", insurance_id = "
            + contract.getInsuranceId() + ", insurance_price = " + contract.getInsurancePrice()
            + ", premium_rate = "
            + contract.getPremiumRate() + ", compensation_amount = " + contract.getCompensationAmount()
            + ", contracted_date = '" + contract.getContractDate() + "', underwrite = " + contract.isUnderWrite()
            + ", pay = " + contract.isPay() + "where contract_id = " + contract.getContractId();
    if (super.update(query)) {
      this.contractList = getContractList();
      return true;
    }
    return false;
  }


  @Override
  public boolean delete(int contractId) {

    String query = "delete from contract where contract_id = " + contractId;
    if (super.delete(query)) {
      this.contractList = this.getContractList();
      return true;
    }

    return false;
  }

  @Override
  public int getSize() {
    return this.contractList.size();
  }
}
