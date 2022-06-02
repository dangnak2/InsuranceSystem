package Insurance;


import Contract.Contract;

import java.util.ArrayList;


public class InsuranceListImpl implements InsuranceList {

	private ArrayList<Insurance> insuranceList;

	public InsuranceListImpl(){
		this.insuranceList = new ArrayList<>();
	}

	public ArrayList<Insurance> getInsuranceList() {
		return insuranceList;
	}

	@Override
	public boolean add(Insurance insurance) {
		if (insuranceList.add(insurance)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(int insuranceId) {
		if (insuranceList.remove(this.get(insuranceId))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Insurance get(int insuranceId) {
		for (Insurance insurance : insuranceList) {
			if (insuranceId == insurance.getId()) {
				return insurance;
			}
		}
		return null;
	}

	@Override
	public void update(Insurance insurance) {
		int index = this.insuranceList.indexOf(insurance);
		this.insuranceList.set(index, insurance);
	}

	@Override
	public int getSize() {
		return this.insuranceList.size();
	}

}
