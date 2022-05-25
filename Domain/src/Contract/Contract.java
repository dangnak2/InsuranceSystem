package Contract;


public class Contract {

	private int contractId;
	private int customerId;
	private int insuranceId;
	private int insurancePrice;
	private int premiumRate;
	private Compensation compensation;

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
}
