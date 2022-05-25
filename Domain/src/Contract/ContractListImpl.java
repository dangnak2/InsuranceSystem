package Contract;


import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public class ContractListImpl implements ContractList {

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
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(int insuranceContractId) {
		if (contractList.remove(this.get(insuranceContractId))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Contract get(int insuranceContractId) {
		for (Contract contract : contractList) {
			if (insuranceContractId == contract.getContractId()) {
				return contract;
			}
		}
		return null;
	}

	@Override
	public void update() {

	}

	@Override
	public int getSize() {
		return this.contractList.size();
	}
}
