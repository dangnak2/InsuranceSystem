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


public class CompensationManagement extends Staff {

    private FireInsurance fireInsurance;
    private CarInsurance carInsurance;
    private SeaInsurance seaInsurance;

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

    public void judgeFireIndemnification(String humanDamage, String buildingDamage, String housingPrice, String basicPrice,  Insurance insurance) {
        fireInsurance = (FireInsurance) insurance;
        fireInsurance.setHumanDamage(Integer.parseInt(humanDamage));
        fireInsurance.setBuildingDamage(Integer.parseInt(buildingDamage));
        fireInsurance.setHousingPrice(Integer.parseInt(housingPrice));
        fireInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
    }
    public void judgeCarIndemnification(String accidentDegree, String humanDamage, String errorRate, String carPrice, String basicPrice, String isDomestic, Insurance insurance) {
        carInsurance = (CarInsurance) insurance;
        carInsurance.setAccidentDegree(Integer.parseInt(accidentDegree));
        carInsurance.setHumanDamage(Integer.parseInt(humanDamage));
        carInsurance.setErrorRate(Integer.parseInt(errorRate));
        carInsurance.setCarPrice(Integer.parseInt(carPrice));
        carInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
        if(isDomestic.equals("국내차")){
            carInsurance.setDomestic(true);
        }else{
            carInsurance.setDomestic(false);
        }
    }
    public void judgeSeaIndemnification(String generalDamage, String revenueDamage, String shipPrice, String basicPrice, Insurance insurance) {
        seaInsurance = (SeaInsurance) insurance;
        seaInsurance.setGeneralDamage(Integer.parseInt(generalDamage));
        seaInsurance.setRevenueDamage(Integer.parseInt(revenueDamage));
        seaInsurance.setShipPrice(Integer.parseInt(shipPrice));
        seaInsurance.setBasicConpensation(Integer.parseInt(basicPrice));
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

            double buildingDamage = 1 + ((FireInsurance) insuranceContract.getInsurance()).getBuildingDamage() / 100;
            double humanDamage = 1 + ((FireInsurance) insuranceContract.getInsurance()).getHumanDamage() / 100;
            double surroundingDamage = 1 + ((FireInsurance) insuranceContract.getInsurance()).getSurroundingDamage() / 100;

            totalPrice *= buildingDamage;
            totalPrice *= humanDamage;
            totalPrice *= surroundingDamage;

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

            double accidentDegree = 1 + ((CarInsurance) insuranceContract.getInsurance()).getAccidentDegree() / 100;
            double humanDamage = 1 + ((CarInsurance) insuranceContract.getInsurance()).getHumanDamage() / 100;
            double errorRate = 1 + 1 - ((CarInsurance) insuranceContract.getInsurance()).getErrorRate();

            totalPrice *= accidentDegree;
            totalPrice *= humanDamage;
            totalPrice *= errorRate;

            compensation.setCompensationAmount(totalPrice);

        } else{
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

            double generalDamage = 1 + this.seaInsurance.getGeneralDamage()/100;
            double revenueDamage = 1 + this.seaInsurance.getRevenueDamage()/100;

            totalPrice *= generalDamage;
            totalPrice *= revenueDamage;

            compensation.setCompensationAmount(totalPrice);
        }
        return compensation;
    }
}

