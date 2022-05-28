package Staff;

import Contract.Compensation;
import Contract.ContractListImpl;
import Contract.Contract;

import Customer.Customer;
import Customer.CustomerListImpl;
import Insurance.FireInsurance;
import Insurance.CarInsurance;
import Insurance.SeaInsurance;
import Insurance.Insurance;
import Insurance.InsuranceListImpl;
import java.util.ArrayList;
import java.util.Date;
import Insurance.FireInsurance.buildingType;
import Insurance.InsuranceList;


public class CompensationManagement extends Staff {

    private FireInsurance fireInsurance;
    private CarInsurance carInsurance;
    private SeaInsurance seaInsurance;

    private InsuranceList insuranceList;

    private int humanDamage;
    // FireInsurance
    private int buildingDamage;
    private int surroundingDamage;
    // CarInsurance
    private int accidentDegree;
    // SeaInsurance
    private int generalDamage;
    private int revenueDamage;

    public CompensationManagement(InsuranceListImpl insuranceList) {
        this.insuranceList = insuranceList;
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

    public void judgeFireIndemnification(String humanDamage, String buildingDamage, String surroundingDamage, String housingPrice, String basicPrice,  Insurance insurance) {
        fireInsurance = (FireInsurance) this.insuranceList.get(insurance.getId());
        fireInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
        fireInsurance.setHousingPrice(Integer.parseInt(housingPrice));

        this.humanDamage = Integer.parseInt(humanDamage);
        this.buildingDamage = Integer.parseInt(buildingDamage);
        this.surroundingDamage = Integer.parseInt(surroundingDamage);

        fireInsurance.setHumanDamage(Integer.parseInt(humanDamage));
        fireInsurance.setBuildingDamage(Integer.parseInt(buildingDamage));

    }
    public void judgeCarIndemnification(String accidentDegree, String humanDamage, String errorRate, String carPrice, String basicPrice, String isDomestic, Insurance insurance) {
        carInsurance = (CarInsurance) this.insuranceList.get(insurance.getId());
        carInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
        carInsurance.setCarPrice(Integer.parseInt(carPrice));

        this.accidentDegree = Integer.parseInt(accidentDegree);
        this.humanDamage = Integer.parseInt(humanDamage);

        carInsurance.setAccidentDegree(Integer.parseInt(accidentDegree));
        carInsurance.setHumanDamage(Integer.parseInt(humanDamage));
        carInsurance.setErrorRate(Integer.parseInt(errorRate));

        if(isDomestic.equals("국내차")){
            carInsurance.setDomestic(true);
        }else{
            carInsurance.setDomestic(false);
        }
    }
    public void judgeSeaIndemnification(String generalDamage, String revenueDamage, String shipPrice, String basicPrice, Insurance insurance) {
        seaInsurance = (SeaInsurance) this.insuranceList.get(insurance.getId());
        seaInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
        seaInsurance.setShipPrice(Integer.parseInt(shipPrice));

        this.generalDamage = Integer.parseInt(generalDamage);
        this.revenueDamage = Integer.parseInt(revenueDamage);

        seaInsurance.setGeneralDamage(Integer.parseInt(generalDamage));
        seaInsurance.setRevenueDamage(Integer.parseInt(revenueDamage));
    }

    public Compensation compensation(Contract insuranceContract) {

        Date date = new Date();
        Compensation compensation = new Compensation();

        if(insuranceContract.getInsurance() instanceof FireInsurance){
            compensation.setCompensationDate(date);
            double totalPrice = 0;
            double basicPrice = insuranceContract.getInsurance().getBasicConpensation();
            if(((FireInsurance) insuranceContract.getInsurance()).getBuildingType() == buildingType.housing){
                totalPrice += basicPrice;
            } else if (((FireInsurance) insuranceContract.getInsurance()).getBuildingType() == buildingType.apartment){
                totalPrice += basicPrice * 1.2;
            } else{
                totalPrice += basicPrice * 1.3;
            }

            if(((FireInsurance) insuranceContract.getInsurance()).getHousingPrice() > 0 &&
                    ((FireInsurance) insuranceContract.getInsurance()).getHousingPrice() <= 100000000){
                totalPrice *= 1;
            } else if(((FireInsurance) insuranceContract.getInsurance()).getHousingPrice() <= 400000000){
                totalPrice *= 1.2;
            } else if(((FireInsurance) insuranceContract.getInsurance()).getHousingPrice() <= 700000000){
                totalPrice *= 1.3;
            } else{
                totalPrice *= 1.4;
            }

            double compensationHumanDamage = (1 +  this.humanDamage / 100) * ((FireInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_HUMAN_DAMAGE();
            double compensationBuildingDamage = (1 +  this.buildingDamage / 100) * ((FireInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_BUILDING_DAMAGE();
            double compensationSurroundingDamage = (1 +  this.surroundingDamage / 100) * ((FireInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_SURROUNGDING_DAMAGE();

            totalPrice += compensationHumanDamage;
            totalPrice += compensationBuildingDamage;
            totalPrice += compensationSurroundingDamage;

            compensation.setCompensationAmount(totalPrice);

        } else if(insuranceContract.getInsurance() instanceof CarInsurance){
            compensation.setCompensationDate(date);

            double totalPrice = 0;
            double basicPrice = insuranceContract.getInsurance().getBasicConpensation();
            if(((CarInsurance) insuranceContract.getInsurance()).isDomestic()){
                totalPrice += basicPrice;
            } else{
                totalPrice += basicPrice * 2;
            }

            if(((CarInsurance) insuranceContract.getInsurance()).getCarPrice() > 0 &&
            ((CarInsurance) insuranceContract.getInsurance()).getCarPrice() <= 30000000){
                totalPrice *= 1;
            } else if(((CarInsurance) insuranceContract.getInsurance()).getCarPrice() <= 50000000){
                totalPrice *= 1.2;
            } else if(((CarInsurance) insuranceContract.getInsurance()).getCarPrice() <= 70000000){
                totalPrice *= 1.3;
            } else{
                totalPrice *= 1.4;
            }

            double compensationHumanDamage = (1 + this.humanDamage / 100) * ((CarInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_HUMAN_DAMAGE();
            double compensationAccidentDegree = (1 + this.accidentDegree / 100) * ((CarInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_ACCIDENT_DEGREE();

            totalPrice += compensationHumanDamage;
            totalPrice += compensationAccidentDegree;

            compensation.setCompensationAmount(totalPrice);

        } else if (insuranceContract.getInsurance() instanceof SeaInsurance){
            compensation.setCompensationDate(date);
            double totalPrice = 0;
            double basicPrice = insuranceContract.getInsurance().getBasicConpensation();

            if(this.seaInsurance.getShipPrice() > 0 &&
            this.seaInsurance.getShipPrice() < 50000000){
                totalPrice += basicPrice;
            } else if(this.seaInsurance.getShipPrice() <= 100000000){
                totalPrice += basicPrice * 1.2;
            } else if(this.seaInsurance.getShipPrice() <= 500000000){
                totalPrice += basicPrice * 1.3;
            } else{
                totalPrice += basicPrice * 1.4;
            }

            double compensationGeneralDamage = (1 + this.generalDamage/100) * ((SeaInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_GENERAL_DAMAGE();
            double compensationrevenueDamage = (1 + this.revenueDamage/100) * ((SeaInsurance) insuranceContract.getInsurance()).getMONEY_BASIS_REVENUE_DAMAGE();

            totalPrice += compensationGeneralDamage;
            totalPrice += compensationrevenueDamage;

            compensation.setCompensationAmount(totalPrice);
        }
        return compensation;
    }
}

