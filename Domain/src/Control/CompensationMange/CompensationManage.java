package Control.CompensationMange;

import Contract.*;
import Customer.*;
import Insurance.*;
import Control.CompensationMange.Indemnification.*;

import java.util.ArrayList;
import java.util.Date;


public class CompensationManage {

    private ContractList contractList;
    private InsuranceList insuranceList;
    private CutomerList customerList;


    public CompensationManage(ContractList contractList, InsuranceList insuranceList, CutomerList cutomerList) {
        this.contractList = contractList;
        this.insuranceList = insuranceList;
        this.customerList = cutomerList;
    }


    //고객 id만 가지고 계약된 보험을 찾아야함 -> name 받을 필요 없음
    //ArrayList로 넘기는건 맞는데 DB 사용 시 ContractListImpl은 DB가 되므로 ArrayList로 넘겨줘야함
    //id는 헷갈리니깐 어떤 것의 id인지 앞에 써주는거 부탁할게용
    public ArrayList<Contract> findInsuranceContracts(String customerId) {
        ArrayList<Contract> customerContractList = new ArrayList<>();

        if (this.contractList instanceof ContractListImpl) {
            for (Contract contract : ((ContractListImpl)this.contractList).getContractList()) {
                if (customerId.equals(contract.getCustomerId())) {
                    customerContractList.add(contract);
                }
            }
        }
        return customerContractList;
    }

    public boolean judgeSubjectIndemnification(AccidentSubjectIndemnification accidentSubjectIndemnification) {
        return accidentSubjectIndemnification.isJudgment();
    }

    public boolean judgeFireIndemnification(FireAccidentCauseIndemnification fireAccidentCauseIndemnification) {
        return fireAccidentCauseIndemnification.isJudgment();
    }
    public boolean judgeCarIndemnification(CarAccidentCauseIndemnification carAccidentCauseIndemnification) {
        return carAccidentCauseIndemnification.isJudgment();
    }
    public boolean judgeSeaIndemnification(SeaAccidentCauseIndemnification seaAccidentCauseIndemnification) {
        return seaAccidentCauseIndemnification.isJudgment();
    }

    public void compensation(int contractId, int humanDamage, int buildingDamage, int surroundingDamage, int carDamage, int generalDamage, int revenueDamage) {

        Date date = new Date();
        Compensation compensation = new Compensation();
        Contract selectContract = this.contractList.get(contractId);
        Insurance selectInsurance = this.insuranceList.get(selectContract.getInsuranceId());
        Customer customer = this.customerList.get(selectContract.getCustomerId());

        double totalPrice = 0;
        double basicPrice = selectInsurance.getBasicConpensation();
        compensation.setCompensationDate(date);


        if(selectInsurance instanceof FireInsurance){


            double compensationHumanDamage = (1 + humanDamage / 100) * ((FireInsurance) selectInsurance).getHumanDamageBasicMoney();
            double compensationBuildingDamage = (1 + buildingDamage / 100) * ((FireInsurance) selectInsurance).getBuildingDamageBasicMoney();
            double compensationSurroundingDamage = (1 + surroundingDamage / 100) * ((FireInsurance) selectInsurance).getSurroundingDamageBasicMoney();

            totalPrice += compensationHumanDamage;
            totalPrice += compensationBuildingDamage;
            totalPrice += compensationSurroundingDamage;


            if(customer.getHouse().getPrice() > 0 &&
                    customer.getHouse().getPrice() <= 100000000){
                totalPrice *= 1;
            } else if(customer.getHouse().getPrice() <= 400000000){
                totalPrice *= 1.2;
            } else if(customer.getHouse().getPrice() <= 700000000){
                totalPrice *= 1.3;
            } else{
                totalPrice *= 1.4;
            }

            compensation.setCompensationAmount(totalPrice);

        } else if(selectInsurance instanceof CarInsurance){


            double compensationHumanDamage = (1 + humanDamage / 100) * ((CarInsurance) selectInsurance).getHumanDamageBasicMoney();
            double compensationAccidentDegree = (1 + carDamage / 100) * ((CarInsurance) selectInsurance).getCarDamageBasicMoney();

            if(customer.getCar().getPrice() > 0 &&
                    customer.getCar().getPrice()  <= 30000000){
                totalPrice *= 1;
            } else if(customer.getCar().getPrice()  <= 50000000){
                totalPrice *= 1.2;
            } else if(customer.getCar().getPrice()  <= 70000000){
                totalPrice *= 1.3;
            } else{
                totalPrice *= 1.4;
            }

            totalPrice += compensationHumanDamage;
            totalPrice += compensationAccidentDegree;

            compensation.setCompensationAmount(totalPrice);

        } else if (selectInsurance instanceof SeaInsurance){


            double compensationGeneralDamage = (1 + generalDamage / 100) * ((SeaInsurance) selectInsurance).getGeneralDamageBasicMoney();
            double compensationRevenueDamage = (1 + revenueDamage / 100) * ((SeaInsurance) selectInsurance).getRevenueDamageBasicMoney();


            totalPrice += compensationGeneralDamage;
            totalPrice += compensationRevenueDamage;

            if(customer.getShip().getPrice() > 0 &&
                    customer.getShip().getPrice() < 50000000){
                totalPrice += basicPrice;
            } else if(customer.getShip().getPrice() <= 100000000){
                totalPrice += basicPrice * 1.2;
            } else if(customer.getShip().getPrice() <= 500000000){
                totalPrice += basicPrice * 1.3;
            } else{
                totalPrice += basicPrice * 1.4;
            }

            compensation.setCompensationAmount(totalPrice);
        }

        selectContract.setCompensation(compensation);

        this.contractList.update(selectContract);
    }
}

