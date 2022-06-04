package Controller.UnderWrite;

import Domain.Contract.Contract;
import Domain.Customer.*;
import Domain.Insurance.*;
import Domain.Staff.*;
import DAO.ContractDAO.ContractDAO;
import DAO.CustomerDAO.CustomerDAO;
import DAO.CustomerDAO.MedicalHistoryDAO;
import DAO.InsuranceDAO.InsuranceDAO;
import DAO.StaffDAO.StaffDAO;

import java.util.ArrayList;

public class UnderWrite {

    private ContractDAO contractDAO;
    private InsuranceDAO insuranceList;
    private CustomerDAO customerList;
    private StaffDAO staffRepository;
    private MedicalHistoryDAO medicalHistoryList;


    public UnderWrite(ContractDAO contractDAO, InsuranceDAO insuranceList, CustomerDAO customerList, StaffDAO staffRepository, MedicalHistoryDAO medicalHistoryList) {
        this.contractDAO = contractDAO;
        this.insuranceList = insuranceList;
        this.customerList = customerList;
        this.staffRepository = staffRepository;
        this.medicalHistoryList = medicalHistoryList;
    }

    public ArrayList<Contract> findCustomerContract(int customerId) {
        ArrayList<Contract> customerContractList = new ArrayList<>();

            for (Contract contract : this.contractDAO.getContractList()) {
                if (customerId == contract.getCustomerId()) {
                    customerContractList.add(contract);
                }
            }

        return customerContractList;
    }

    public Customer getCustomer(int customerId) {
        Customer customer = this.customerList.get(customerId);
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }

    public Insurance getInsurance(int insuranceId) {
            for (Insurance insurance : this.insuranceList.getInsuranceList()) {
                if (insurance.getId() == insuranceId) {
                    return insurance;
                }
            }
        return null;
    }

    public Contract getContract(int contractId) {
            for (Contract contract : this.contractDAO.getContractList()) {
                if (contractId == contract.getContractId()) {
                    return contract;
                }
            }

        return null;
    }

    public boolean checkDangerJob(Customer customer) {
        return customer.getJob().isDanger();
    }

    public boolean checkAgeDangerJob(Customer customer) {
        if (customer.getAge() > customer.getJob().getMaxAge()) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkDisease(Customer customer) {

        return medicalHistoryList.get(customer.getId()).getMyDisease().isPass();
    }

    public void passUnderwrite(Contract contract, Staff staff) {
        contract.setUnderWrite(true);
        staff.setResult(staff.getResult()+1);

        this.staffRepository.update(staff);
        this.contractDAO.update(contract);
    }
}
