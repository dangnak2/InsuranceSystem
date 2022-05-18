package Staff;
import java.util.ArrayList;
import java.util.Date;

import Contract.InsuranceContractListImpl;
import Insurance.*;

public class Design extends Staff {


	public Design(){

	}


	public void approve(){

	}

	public void authorize(Insurance insurance, InsuranceListImpl insuranceList) {
		Date date = new Date();
		for (Insurance findInsurance : insuranceList.getInsuranceList()) {
			if (findInsurance == insurance) {
				insurance.setAuthorization(true);
				insurance.setAuthorizationDate(date);
			}
		}
		insuranceList.delete(insurance.getId());
		insuranceList.add(insurance);

	}

	public void design(String name, String explanation, String premium, InsuranceListImpl insuranceList){
		Date date = new Date();

		Insurance insurance = new Insurance();

		insurance.setId(insuranceList.getInsuranceList().size()+1);
		insurance.setAuthorization(false);
		insurance.setCreatedDate(date);
		insurance.setModifiedDate(date);
		insurance.setName(name);
		insurance.setExplanation(explanation);
		insurance.setPremium(Integer.parseInt(premium));

		insuranceList.add(insurance);

	}

	public void manage() {

	}

	public int computeAuthorizeCount(ArrayList<Insurance> insuranceList) {
		int count = 0;
		for (Insurance insurance : insuranceList) {
			if (insurance.isAuthorization()) {
				count++;
			}
		}
		return count;
	}


}
