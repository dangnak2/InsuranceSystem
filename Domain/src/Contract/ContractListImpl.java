package Contract;


import DB.DBConnector;

import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public class ContractListImpl extends DBConnector implements ContractList {

	private ArrayList<Contract> contractList;

	public ContractListImpl(){
		this.contractList = new ArrayList<>();
	}

	public ArrayList<Contract> getContractList() {
		return contractList;
	}


	@Override
	public boolean add(Contract contract) {
		if (contractList.add(contract)) {
			String query = "INSERT INTO contract VALUES ("
					+ contract.getContractId() + ",'" + contract.getCustomerId() + "','" + contract.getSalesId()
					+ "','" + contract.getInsuranceId() + "'," + contract.getInsurancePrice() + ",'" + contract.getPremiumRate() + "','"
					+ contract.getCompensation() + "','" + contract.getCreadtedDate() + ");";
			super.add(query);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(int contractId) {
		if (contractList.remove(this.get(contractId))) {
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
	public void update(Contract contract) {
		int index = this.contractList.indexOf(contract);
		this.contractList.set(index, contract);
	}

	@Override
	public int getSize() {
		return this.contractList.size();
	}
}
