package Control.Design;

import Insurance.*;

import java.util.Date;

public class Design {

	private InsuranceList insuranceList;

	public Design(InsuranceList insuranceList) {
		this.insuranceList = insuranceList;
	}


	public void approve(){

	}

	public void authorize(Insurance insurance) {
		Date date = new Date();
		if (insuranceList instanceof InsuranceListImpl) {
			for (Insurance findInsurance : ((InsuranceListImpl) insuranceList).getInsuranceList()) {
				if (findInsurance == insurance) {
					insurance.setAuthorization(true);
					insurance.setAuthorizationDate(date);
				}
			}
		}
		insuranceList.delete(insurance.getId());
		insuranceList.add(insurance);

	}

	public Insurance design(String type, String name, String explanation, String premium){
		Date date = new Date();
		Insurance insurance = null;
		if (type.equals("화재")){
			insurance = new FireInsurance();
		} else if (type.equals("자동차")){
			insurance = new CarInsurance();
		} else if (type.equals("해상")){
			insurance = new SeaInsurance();
		}



		insurance.setId(insuranceList.getSize()+1);
		insurance.setAuthorization(false);
		insurance.setCreatedDate(date);
		insurance.setModifiedDate(date);
		insurance.setName(name);
		insurance.setExplanation(explanation);
		insurance.setPremium(Integer.parseInt(premium));

		insuranceList.add(insurance);

		return insurance;
	}

	public void manage() {

	}

	public int computeAuthorizeCount() {
		int count = 0;
		if (insuranceList instanceof InsuranceListImpl) {
			for (Insurance insurance : ((InsuranceListImpl) insuranceList).getInsuranceList()) {
				if (insurance.isAuthorization()) {
					count++;
				}
			}
		}
		return count;
	}

	public int computeNotAuthorizeCount() {
		int count = 0;
		if (insuranceList instanceof InsuranceListImpl) {
			for (Insurance insurance : ((InsuranceListImpl) insuranceList).getInsuranceList()) {
				if (!insurance.isAuthorization()) {
					count++;
				}
			}
		}
		return insuranceList.getSize() - count;
	}


	public int computeTotalSize() {
		return this.insuranceList.getSize();
	}
}
