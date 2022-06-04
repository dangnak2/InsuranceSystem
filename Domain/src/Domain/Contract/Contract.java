package Domain.Contract;


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

	public double getCompensationAmount() {
		return compensationAmount;
	}

	public void setCompensationAmount(double compensationAmount) {
		this.compensationAmount = compensationAmount;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	private double compensationAmount;
	private Date contractDate;
	private boolean underWrite;

	public Contract(){

	}

	public boolean isUnderWrite() {
		return this.underWrite;
	}

	public void setUnderWrite(boolean underWrite) {
		this.underWrite = underWrite;
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

}
