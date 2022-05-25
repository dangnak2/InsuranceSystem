package Staff;

import Contract.Compensation;
import Contract.ContractListImpl;
import Contract.Contract;

import Customer.Customer;
import Customer.CustomerListImpl;
import Insurance.Insurance;
import Insurance.InsuranceListImpl;
import java.util.ArrayList;

public class CompensationManagement extends Staff {

    public CompensationManagement() {

    }


    public ContractListImpl findInsuranceContracts(String id, String name,
        ContractListImpl insuranceContractListImpl) {
        ArrayList<Contract> insuranceContracts = insuranceContractListImpl.getContractList();
        ContractListImpl customerContractsList = new ContractListImpl();
        CustomerListImpl customerList = new CustomerListImpl();
        InsuranceListImpl insuranceList = new InsuranceListImpl();

        for (Contract insuranceContract : insuranceContracts) {
            Customer customer = customerList.get(insuranceContract.getCustomerId());
            Insurance insurance = insuranceList.get(insuranceContract.getInsuranceId());
            if (insuranceContract.getCustomerId() == Integer.parseInt(id)
                && name.equals(customer.getName()) && insurance.isAuthorization()) {
                customerContractsList.add(insuranceContract);
            }
        }

        return customerContractsList;
    }


    public void judgeIndemnification(String selectCause, String selectOriginator,
        String selectOccurringArea, String brokenCondition, String humanDamage,
        String surroundingDamage, Contract insuranceContract) {


    }

    public Compensation compensation(Contract insuranceContract) {
        //계산 식에 따라 달라짐
//        Compensation compensation = new Compensation();
//        compensation.setAccident(insuranceContract.getAccident());
//        compensation.setAccount(insuranceContract.getPolicyholder().getAccount());
//        compensation.setCompensationAmount((insuranceContract.getAccident().getBrokenCondition() +insuranceContract.getAccident().getHumanDamage()
//                + insuranceContract.getAccident().getSurroundingDamage()) * 1000 );
//        return compensation;
        return null;
    }
}

