package Policyholder;


import java.util.ArrayList;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ���� 4:51:10
 */
public class PolicyholderListImpl implements PolicyholderList {//impl이 저장소

	private ArrayList<Policyholder> policyholderList;

	public PolicyholderListImpl(){
		this.policyholderList = new ArrayList<>();
	}

	public ArrayList<Policyholder> getPolicyholderList() {
		return policyholderList;
	}

	@Override
	public boolean add(Policyholder policyholder) {
		if (this.policyholderList.add(policyholder)) {
			return true;
		}else
			return false;
	}

	@Override
	public boolean delete(int policyholderId) {
		if (this.policyholderList.remove(this.get(policyholderId))) {
			return true;
		}else
			return false;
	}

	@Override
	public Policyholder get(int policyholderId) {
		for (Policyholder policyholder : policyholderList) {
			if (policyholderId == policyholder.getId()) {
				return policyholder;
			}
		}
		return null;
	}
}
