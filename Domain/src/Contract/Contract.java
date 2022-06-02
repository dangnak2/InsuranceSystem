package Contract;


import Insurance.Insurance;

import java.util.Date;

public class Contract {

	private int contractId;
	private int customerId;

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	private int salesId;
	private int insuranceId;
	private int insurancePrice;
	private int premiumRate;
	private Compensation compensation;
	private Insurance insurance;
	private Date creadtedDate;

	public Contract(){

	}

	public Date getCreadtedDate() {
		return creadtedDate;
	}

	public void setCreadtedDate(Date creadtedDate) {
		this.creadtedDate = creadtedDate;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getInsurancePrice() {
		return insurancePrice;
	}

	public void setInsurancePrice(int insurancePrice) {
		this.insurancePrice = insurancePrice;
	}

	public int getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(int premiumRate) {
		this.premiumRate = premiumRate;
	}

	public Compensation getCompensation() {
		return compensation;
	}

	public void setCompensation(Compensation compensation) {
		this.compensation = compensation;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
}
