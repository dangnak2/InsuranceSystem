package Contract;


import DB.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractListImpl extends DBConnector implements ContractList {

  private ArrayList<Contract> contractList;

  public ContractListImpl() {
    this.contractList = new ArrayList<>();
    super.getConnection();
    this.contractList = this.getContractList();
  }

  public ArrayList<Contract> getContractList() {
    String query = "select * from contract;";
    ResultSet rs = super.retreive(query);
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
        + "');";
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
    String query = "update staff set "
        + "customer_id = " + contract.getCustomerId() + ", sales_id = " + contract.getSalesId()
        + ", insurance_id = "
        + contract.getInsuranceId() + ", insurancePrice = " + contract.getInsurancePrice()
        + ", premium_rate = "
        + contract.getPremiumRate() + ", compensation_amount = " + contract.getCompensationAmount()
        + ", contract_date = " + contract.getContractDate()
        + " where id =" + contract.getContractId();
    if (super.update(query)) {
      this.contractList = getContractList();
      return true;
    }
    return false;
  }


  @Override
  public boolean delete(int contractId) {

    String query = "delete from staff where id=" + contractId;
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
