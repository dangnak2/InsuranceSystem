package Staff;

import Contract.Accident;
import Contract.Compensation;
import Contract.InsuranceContract;
import Contract.InsuranceContractListImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class CompensationManagement extends Staff {

    public CompensationManagement() {

    }


    public InsuranceContractListImpl findInsuranceContracts(String id, String name, InsuranceContractListImpl insuranceContractListImpl) {
        ArrayList<InsuranceContract> insuranceContracts = insuranceContractListImpl.getInsuranceContractList();
        InsuranceContractListImpl policyholderContractsList = new InsuranceContractListImpl();

        for (InsuranceContract insuranceContract : insuranceContracts) {
            if (insuranceContract.getPolicyholder().getId() == Integer.parseInt(id)
                    && name.equals(insuranceContract.getPolicyholder().getName()) && insuranceContract.getInsurance().isAuthorization()) {
                policyholderContractsList.add(insuranceContract);
            }
        }

        return policyholderContractsList;
    }


    public void judgeIndemnification(String selectCause, String selectOriginator, String selectOccurringArea, String brokenCondition, String humanDamage, String surroundingDamage, InsuranceContract insuranceContract) {

        Accident accident = new Accident();

        accident.setCauses(Integer.parseInt(selectCause) - 1);
        accident.setOccurringArea(Integer.parseInt(selectOccurringArea) - 1);
        accident.setOriginator(Integer.parseInt(selectOriginator) - 1);
        accident.setBrokenCondition(Integer.parseInt(brokenCondition));
        accident.setHumanDamage(Integer.parseInt(humanDamage));
        accident.setSurroundingDamage(Integer.parseInt(surroundingDamage));
        insuranceContract.setAccident(accident);

    }
    public Compensation compensation(InsuranceContract insuranceContract) {
        //계산 식에 따라 달라짐
        Compensation compensation = new Compensation();
        compensation.setAccident(insuranceContract.getAccident());
        compensation.setAccount(insuranceContract.getPolicyholder().getAccount());
        compensation.setCompensationAmount((insuranceContract.getAccident().getBrokenCondition() +insuranceContract.getAccident().getHumanDamage()
                + insuranceContract.getAccident().getSurroundingDamage()) * 1000 );
        return compensation;
    }
}
