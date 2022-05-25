package Staff;

import Contract.Contract;
import Contract.ContractListImpl;
import Customer.Customer;
import Customer.CustomerListImpl;
import Customer.MedicalHistory;
import Customer.MedicalHistory.disease;
import Insurance.Insurance;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sales extends Staff {

	private int contractCount;
	public Contract insuranceContract;
	MedicalHistory medicalHistory;
	Customer customer;

	public Sales(){

	}

	public void addContract(Contract insuranceContract){

	}

	public void addPolicyholder(Customer policyHolder){

	}

	public boolean deleteContract(String selectContract, ContractListImpl insuranceContractList, ContractListImpl findContract){
		Contract cancleContract = findContract.get(Integer.parseInt(selectContract));

		if (cancleContract != null) {
			if (insuranceContractList.delete(cancleContract.getContractId())) {
				return true;
			}
		}
			return false;
	}

	public void deletePolicyholder(){

	}

	public void findContract(String id, String name, ContractListImpl insuranceContractList, ContractListImpl findContract){
		CustomerListImpl customerListImpl = new CustomerListImpl();


		for (Contract insuranceContract : insuranceContractList.getContractList()) {
			Customer customer = customerListImpl.get(insuranceContract.getCustomerId());
			if (Integer.parseInt(id) == insuranceContract.getCustomerId()
					&& name.equals(customer.getName())) {
				findContract.add(insuranceContract);
			}
		}
	}

	public int getContractCount(){
		return 0;
	}

	public int computeCustomer(ContractListImpl insuranceContractList) {
		ArrayList<Integer> check = new ArrayList<>();
		int count = 0;
		for (Contract insuranceContract : insuranceContractList.getContractList()) {
			if (!check.isEmpty()) {
				for (Integer id : check) {
					if (id == insuranceContract.getCustomerId()) {
						break;
					}
				}
				check.add(insuranceContract.getCustomerId());
				count++;
			} else {
				check.add(insuranceContract.getCustomerId());
				count++;
				continue;
			}
		}
		return count;
	}

	public int computePayment(ContractListImpl insuranceContractList) {
		int count = 0;
		for (Contract insuranceContract : insuranceContractList.getContractList()) {
			CustomerListImpl customerList = new CustomerListImpl();
			Customer customer = customerList.get(insuranceContract.getCustomerId());
			if (!customer.isTestPay()) {
				count++;
			}
		}
		return count;
	}

	public int computeThisMonthJoin(ContractListImpl insuranceContractList) {
		Date date = new Date();
		int count = 0;
		for (Contract insuranceContract : insuranceContractList.getContractList()) {
			CustomerListImpl customerList = new CustomerListImpl();
			Customer customer = customerList.get(insuranceContract.getCustomerId());
			if (date.getMonth() == customer.getJoinDate().getMonth()) {
				count++;
			}
		}
		return count;
	}

	public void insuracePayment(ContractListImpl insuranceContractList) {
		ArrayList<Contract> insuranceContracts = insuranceContractList.getContractList();

		for (Contract insuranceContract : insuranceContracts) {
			CustomerListImpl customerList = new CustomerListImpl();
			Customer customer = customerList.get(insuranceContract.getCustomerId());
			if (!customer.isPay()) {
				customer.setPay(true);
			}
		}
	}

	public void setContractCount(int contractCount){

	}



	public Customer joinCustomer(String name, String SSN, String sex, String email, String phoneNumber, String account, String address, String medicalName, String medicalYear, String medicalCure, CustomerListImpl customerList) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setSSN(SSN);
		if (sex.equals("1")) {
			customer.setSex(true);
		} else if (sex.equals("2")) {
			customer.setSex(false);
		}
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setAccount(account);
		customer.setAddress(address);

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
		customer.setMedicalHistory(medicalHistory);

		customerList.add(customer);
		return customer;
	}

	public void createContract(Customer customer, Insurance selectInsurance, ContractListImpl contractList) {
		Contract contract = new Contract();
		contract.setContractId(contractList.getContractList().size() + 1);
		contract.setInsuranceId(selectInsurance.getId());
		contract.setCustomerId(customer.getId());
		contract.setSalesId(this.getId());

		contractList.add(contract);
	}
}
