package Contract;


import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public class InsuranceContractListImpl implements InsuranceContractList {

	private ArrayList<InsuranceContract> insuranceContractList;

	public InsuranceContractListImpl(){
		this.insuranceContractList = new ArrayList<>();
	}

	public ArrayList<InsuranceContract> getInsuranceContractList() {
		return insuranceContractList;
	}


	@Override
	public boolean add(InsuranceContract insuranceContract) {
		if (insuranceContractList.add(insuranceContract)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(int insuranceContractId) {
		if (insuranceContractList.remove(this.get(insuranceContractId))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public InsuranceContract get(int insuranceContractId) {
		for (InsuranceContract insuranceContract : insuranceContractList) {
			if (insuranceContractId == insuranceContract.getContractId()) {
				return insuranceContract;
			}
		}
		return null;
	}
}
