package Domain.Contract;


import java.util.Date;

public class Contract {

	private int contractId;
	private int customerId;
	private int salesId;
	private int insuranceId;
	private int insurancePrice;
	private int premiumRate;
	private double compensationAmount;
	private Date contractDate;
	private boolean underWrite;
	private boolean pay;

	public Contract(){

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

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
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

	public boolean isUnderWrite() {
		return underWrite;
	}

	public void setUnderWrite(boolean underWrite) {
		this.underWrite = underWrite;
	}

	public boolean isPay() {
		return pay;
	}

	public void setPay(boolean pay) {
		this.pay = pay;
	}
}
