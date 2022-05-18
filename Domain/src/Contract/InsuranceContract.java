package Contract;


import Insurance.Insurance;
import Policyholder.Policyholder;

import java.util.Scanner;

/**
 * @author crossover
 * @version 1.0
 * @created 09-5-2022 ???? 4:51:09
 */
public class InsuranceContract {

	InsuranceContract insuranceContract;
	private Accident accident;
	private int contractId;
	private Insurance insurance;
	private int insurancePrice;
	private Policyholder policyholder;
	private int premiumRate;
	private int salesId;


	public InsuranceContract(){

	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public int getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(int insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public Policyholder getPolicyholder() {
		return policyholder;
	}

	public void setPolicyholder(Policyholder policyholder) {
		this.policyholder = policyholder;
	}

	public int getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(int premiumRate) {
		this.premiumRate = premiumRate;
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public void calculatePremiumRate() {

	}

	public void calculatePrice() {

	}


}
