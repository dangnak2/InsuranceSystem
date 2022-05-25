package Staff;
import Insurance.Insurance.InsuranceType;
import java.util.ArrayList;
import java.util.Date;

import Contract.ContractListImpl;
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

	public Insurance design(String type, String name, String explanation, String premium, InsuranceListImpl insuranceList){
		Date date = new Date();
		Insurance insurance = null;
		if (type.equals("화재")){
			insurance = new FireInsurance();
			insurance.setType(InsuranceType.Fire);
		} else if (type.equals("자동차")){
			insurance = new CarInsurance();
			insurance.setType(InsuranceType.Car);
		} else if (type.equals("해상")){
			insurance = new SeaInsurance();
			insurance.setType(InsuranceType.Sea);
		}



		insurance.setId(insuranceList.getInsuranceList().size()+1);
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
