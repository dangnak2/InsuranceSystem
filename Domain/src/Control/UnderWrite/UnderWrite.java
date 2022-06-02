package Control.UnderWrite;

import Contract.Contract;
import Contract.*;
import Customer.*;
import Insurance.*;
import Staff.*;

import java.util.ArrayList;

public class UnderWrite {

    private ContractList contractList;
    private InsuranceList insuranceList;
    private CustomerList customerList;
    private StaffList staffList;
    private MedicalHistoryList medicalHistoryList;


    public UnderWrite(ContractList contractList, InsuranceList insuranceList, CustomerList customerList, StaffList staffList, MedicalHistoryList medicalHistoryList) {
        this.contractList = contractList;
        this.insuranceList = insuranceList;
        this.customerList = customerList;
        this.staffList = staffList;
        this.medicalHistoryList = medicalHistoryList;
    }

    public ArrayList<Contract> findCustomerContract(int customerId) {
        ArrayList<Contract> customerContractList = new ArrayList<>();

        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl)this.contractList).getContractList()) {
                if (customerId == contract.getCustomerId()) {
                    customerContractList.add(contract);
                }
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
        if (this.insuranceList instanceof InsuranceListImpl) {
            for (Insurance insurance : ((InsuranceListImpl) this.insuranceList).getInsuranceList()) {
                if (insurance.getId() == insuranceId) {
                    return insurance;
                }
            }
        }
        return null;
    }

    public Contract getContract(int contractId) {
        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl)this.contractList).getContractList()) {
                if (contractId == contract.getContractId()) {
                    return contract;
                }
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

        this.staffList.update(staff);
        this.contractList.update(contract);
    }
}
