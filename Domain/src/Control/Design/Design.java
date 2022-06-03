package Control.Design;

import Insurance.*;
import Insurance.Insurance.Type;
import Staff.Staff;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Design {

	private InsuranceList insuranceList;
	private FireInsuranceList fireInsuranceList;
	private CarInsuranceList carInsuranceList;
	private SeaInsuranceList seaInsuranceList;

	public Design(InsuranceList insuranceList, FireInsuranceList fireInsuranceList, CarInsuranceList carInsuranceList, SeaInsuranceList seaInsuranceList) {
		this.insuranceList = insuranceList;
		this.fireInsuranceList = fireInsuranceList;
		this.carInsuranceList = carInsuranceList;
		this.seaInsuranceList = seaInsuranceList;
	}



	public boolean authorize(int insuranceId) {
		Insurance insurance = this.insuranceList.get(insuranceId);
		if (insurance == null) {
			return false;
		}
		insurance.setAuthorization(true);
		insurance.setAuthorizedDate(Timestamp.valueOf(LocalDateTime.now()));
		return insuranceList.update(insurance);
	}


	public Insurance design(int type, String name, String explanation, int premium, int surroundingDamageBasicMoney, int humanDamageBasicMoney, int buildingDamageBasicMoney
												, int carDamageBasicMoney, int generalDamageBasicMoney, int revenueDamageBasicMoney, Staff staff){
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

		insurance.setId(insuranceList.getSize()+1);
		insurance.setType(Type.values()[type - 1]);
		insurance.setAuthorization(false);
		insurance.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		insurance.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		insurance.setName(name);
		insurance.setExplanation(explanation);
		insurance.setPremium(premium);
		insuranceList.add(insurance);

		if (insurance instanceof FireInsurance) {
			((FireInsurance) insurance).setFireInsurance_id(insurance.getId());
			((FireInsurance) insurance).setSurroundingDamageBasicMoney(surroundingDamageBasicMoney);
			((FireInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);
			((FireInsurance) insurance).setBuildingDamageBasicMoney(buildingDamageBasicMoney);

			fireInsuranceList.add((FireInsurance) insurance);
		}else if (insurance instanceof CarInsurance) {
			((CarInsurance) insurance).setCarInsurance_id(insurance.getId());
			((CarInsurance) insurance).setCarDamageBasicMoney(carDamageBasicMoney);
			((CarInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);

			carInsuranceList.add((CarInsurance) insurance);
		}else if (insurance instanceof SeaInsurance) {
			((SeaInsurance) insurance).setSeeInsurance_id(insurance.getId());
			((SeaInsurance) insurance).setGeneralDamageBasicMoney(generalDamageBasicMoney);
			((SeaInsurance) insurance).setRevenueDamageBasicMoney(revenueDamageBasicMoney);

			seaInsuranceList.add((SeaInsurance) insurance);
		}


		staff.setResult(staff.getResult()+1);
		return insurance;
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
