package Control.Design;

import Customer.Car;
import Insurance.*;
import Insurance.Insurance.Type;

import java.util.ArrayList;
import java.util.Date;

public class Design {

	private InsuranceList insuranceList;

	public Design(InsuranceList insuranceList) {
		this.insuranceList = insuranceList;
	}


	public void approve(){

	}

	public boolean authorize(int insuranceId) {
		Insurance insurance = this.insuranceList.get(insuranceId);
		if (insurance == null) {
			return false;
		}
		Date date = new Date();
		if (insuranceList instanceof InsuranceListImpl) {
			for (Insurance findInsurance : ((InsuranceListImpl) insuranceList).getInsuranceList()) {
				if (findInsurance == insurance) {
					insurance.setAuthorization(true);
					insurance.setAuthorizationDate(date);
				}
			}
		}
		insuranceList.update(insurance);
		return true;
	}


	public Insurance design(int type, String name, String explanation, int premium, int surroundingDamageBasicMoney, int humanDamageBasicMoney, int buildingDamageBasicMoney
			, int carDamageBasicMoney, int generalDamageBasicMoney, int revenueDamageBasicMoney){
		Date date = new Date();
		Insurance insurance = null;
		if (type == 1) {
			insurance = new FireInsurance();
		} else if (type == 2) {
			insurance = new CarInsurance();
		} else if (type == 3) {
			insurance = new SeaInsurance();
		} else {
			return null;
		}

		if (insurance instanceof FireInsurance) {
			((FireInsurance) insurance).setSurroundingDamageBasicMoney(surroundingDamageBasicMoney);
			((FireInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);
			((FireInsurance) insurance).setBuildingDamageBasicMoney(buildingDamageBasicMoney);
		}else if (insurance instanceof CarInsurance) {
			((CarInsurance) insurance).setCarDamageBasicMoney(carDamageBasicMoney);
			((CarInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);
		}else if (insurance instanceof SeaInsurance) {
			((SeaInsurance) insurance).setGeneralDamageBasicMoney(generalDamageBasicMoney);
			((SeaInsurance) insurance).setRevenueDamageBasicMoney(revenueDamageBasicMoney);
		}


		insurance.setId(insuranceList.getSize()+1);
		insurance.setType(Type.values()[type - 1]);
		insurance.setAuthorization(false);
		insurance.setCreatedDate(date);
		insurance.setModifiedDate(date);
		insurance.setName(name);
		insurance.setExplanation(explanation);
		insurance.setPremium(premium);

		insuranceList.add(insurance);

		return insurance;
	}

	public void manage() {

	}

	public ArrayList<Insurance> getInsuranceList() {
		if (this.insuranceList instanceof InsuranceListImpl) {
			return ((InsuranceListImpl) this.insuranceList).getInsuranceList();
		}
		return null;
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
		return count;
	}


	public int computeTotalSize() {
		return this.insuranceList.getSize();
	}
}
