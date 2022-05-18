package Staff;

import Contract.InsuranceContract;
import Contract.InsuranceContractListImpl;
import Insurance.Insurance;
import Policyholder.MedicalHistory;
import Policyholder.MedicalHistory.disease;
import Policyholder.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sales extends Staff {

	private int contractCount;
	public InsuranceContract insuranceContract;
	MedicalHistory medicalHistory;
	Policyholder policyholder;
	Scanner sc = new Scanner(System.in);

	public Sales(){

	}

	public void addContract(InsuranceContract insuranceContract){

	}

	public void addPolicyholder(Policyholder policyHolder){

	}

	public boolean deleteContract(String selectContract, InsuranceContractListImpl insuranceContractList, InsuranceContractListImpl findContract){
		InsuranceContract cancleContract = findContract.get(Integer.parseInt(selectContract));

		if (cancleContract != null) {
			if (insuranceContractList.delete(cancleContract.getContractId())) {
				return true;
			}
		}
			return false;
	}

	public void deletePolicyholder(){

	}

	public void findContratct(String id, String name, InsuranceContractListImpl insuranceContractList, InsuranceContractListImpl findContract){
		for (InsuranceContract insuranceContract : insuranceContractList.getInsuranceContractList()) {
			if (Integer.parseInt(id) == insuranceContract.getPolicyholder().getId()
					&& name.equals(insuranceContract.getPolicyholder().getName())) {
				findContract.add(insuranceContract);
			}
		}
	}

	public int getContractCount(){
		return 0;
	}

	public int computePolicyholder(InsuranceContractListImpl insuranceContractList) {
		ArrayList<Integer> check = new ArrayList<>();
		int count = 0;
		for (InsuranceContract insuranceContract : insuranceContractList.getInsuranceContractList()) {
			if (!check.isEmpty()) {
				for (Integer id : check) {
					if (id == insuranceContract.getPolicyholder().getId()) {
						break;
					}
				}
				check.add(insuranceContract.getPolicyholder().getId());
				count++;
			} else {
				check.add(insuranceContract.getPolicyholder().getId());
				count++;
				continue;
			}
		}
		return count;
	}

	public int computePayment(InsuranceContractListImpl insuranceContractList) {
		int count = 0;
		for (InsuranceContract insuranceContract : insuranceContractList.getInsuranceContractList()) {
			if (!insuranceContract.getPolicyholder().isTestPay()) {
				count++;
			}
		}
		return count;
	}

	public int computeThisMonthJoin(InsuranceContractListImpl insuranceContractList) {
		Date date = new Date();
		int count = 0;
		for (InsuranceContract insuranceContract : insuranceContractList.getInsuranceContractList()) {
			if (date.getMonth() == insuranceContract.getPolicyholder().getJoinDate().getMonth()) {
				count++;
			}
		}
		return count;
	}

	public void insuracePayment(InsuranceContractListImpl insuranceContractList) {
		ArrayList<InsuranceContract> insuranceContracts = insuranceContractList.getInsuranceContractList();

		for (InsuranceContract insuranceContract : insuranceContracts) {
			if (!insuranceContract.getPolicyholder().isPay()) {
				insuranceContract.getPolicyholder().setPay(true);
			}
		}
	}

	public void setContractCount(int contractCount){

	}



	public Policyholder joinPolicyholder(String name, String SSN, String sex, String email, String phoneNumber, String account, String address, String medicalName, String medicalYear, String medicalCure, PolicyholderListImpl policyholderList) {
		Policyholder policyholder = new Policyholder();
		policyholder.setName(name);
		policyholder.setSSN(SSN);
		if (sex.equals("1")) {
			policyholder.setSex(true);
		} else if (sex.equals("2")) {
			policyholder.setSex(false);
		}
		policyholder.setEmail(email);
		policyholder.setPhoneNumber(phoneNumber);
		policyholder.setAccount(account);
		policyholder.setAddress(address);

		MedicalHistory medicalHistory = new MedicalHistory();
		switch (Integer.parseInt(medicalName)) {
			case 1:
				medicalHistory.setMyDisease(disease.결핵);
				break;
			case 2:
				medicalHistory.setMyDisease(disease.수두);
				break;
			case 3:
				medicalHistory.setMyDisease(disease.홍역);
				break;
			default:
				break;
		}
		medicalHistory.setHistoryYear(Integer.parseInt(medicalYear));
		if (medicalCure.equals("1")) {
			medicalHistory.setCureComplete(true);
		} else if (medicalCure.equals("2")) {
			medicalHistory.setCureComplete(false);
		}
		policyholder.setMedicalHistory(medicalHistory);

		policyholderList.add(policyholder);
		return policyholder;
	}

	public void createContract(Policyholder policyholder, Insurance selectInsurance, InsuranceContractListImpl insuranceContractList) {
		InsuranceContract insuranceContract = new InsuranceContract();
		insuranceContract.setContractId(insuranceContractList.getInsuranceContractList().size() + 1);
		insuranceContract.setInsurance(selectInsurance);
		insuranceContract.setPolicyholder(policyholder);
		insuranceContract.setSalesId(this.getId());

		insuranceContractList.add(insuranceContract);
	}
}
