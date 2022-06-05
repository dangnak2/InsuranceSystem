package Controller.Design;

import DAO.InsuranceDAO.DBInsuranceDAO;
import DAO.StaffDAO.StaffDAO;
import Domain.Insurance.*;
import Domain.Insurance.Insurance.Type;
import Domain.Staff.Staff;
import DAO.InsuranceDAO.CarInsuranceDAO;
import DAO.InsuranceDAO.FireInsuranceDAO;
import DAO.InsuranceDAO.InsuranceDAO;
import DAO.InsuranceDAO.SeaInsuranceDAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Design {

	private InsuranceDAO insuranceDAO;
	private FireInsuranceDAO fireInsuranceDAO;
	private CarInsuranceDAO carInsuranceDAO;
	private SeaInsuranceDAO seaInsuranceDAO;
	private StaffDAO staffDAO;

	public Design(InsuranceDAO insuranceDAO, FireInsuranceDAO fireInsuranceDAO, CarInsuranceDAO carInsuranceDAO, SeaInsuranceDAO seaInsuranceDAO, StaffDAO staffDAO) {
		this.insuranceDAO = insuranceDAO;
		this.fireInsuranceDAO = fireInsuranceDAO;
		this.carInsuranceDAO = carInsuranceDAO;
		this.seaInsuranceDAO = seaInsuranceDAO;
		this.staffDAO = staffDAO;
	}



	public boolean authorize(int insuranceId) {
		Insurance insurance = this.insuranceDAO.get(insuranceId);
		if (insurance == null) {
			return false;
		}
		insurance.setAuthorization(true);
		insurance.setAuthorizedDate(Timestamp.valueOf(LocalDateTime.now()));
		return insuranceDAO.update(insurance);
	}

	public boolean deleteInsurance(int insuranceId) {
		return insuranceDAO.delete(insuranceId);
	}


	public Insurance design(int type, String name, String explanation, int premium, int surroundingDamageBasicMoney, int humanDamageBasicMoney, int buildingDamageBasicMoney
												, int carDamageBasicMoney, int generalDamageBasicMoney, int revenueDamageBasicMoney, Staff staff){
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

		insurance.setId(setInsuranceId());
		insurance.setType(Type.values()[type - 1]);
		insurance.setAuthorization(false);
		insurance.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		insurance.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		insurance.setName(name);
		insurance.setExplanation(explanation);
		insurance.setPremium(premium);
		insuranceDAO.add(insurance);

		if (insurance instanceof FireInsurance) {
			((FireInsurance) insurance).setFireInsuranceId(insurance.getId());
			((FireInsurance) insurance).setSurroundingDamageBasicMoney(surroundingDamageBasicMoney);
			((FireInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);
			((FireInsurance) insurance).setBuildingDamageBasicMoney(buildingDamageBasicMoney);

			fireInsuranceDAO.add((FireInsurance) insurance);
		}else if (insurance instanceof CarInsurance) {
			((CarInsurance) insurance).setCarInsuranceId(insurance.getId());
			((CarInsurance) insurance).setCarDamageBasicMoney(carDamageBasicMoney);
			((CarInsurance) insurance).setHumanDamageBasicMoney(humanDamageBasicMoney);

			carInsuranceDAO.add((CarInsurance) insurance);
		}else if (insurance instanceof SeaInsurance) {
			((SeaInsurance) insurance).setSeaInsuranceId(insurance.getId());
			((SeaInsurance) insurance).setGeneralDamageBasicMoney(generalDamageBasicMoney);
			((SeaInsurance) insurance).setRevenueDamageBasicMoney(revenueDamageBasicMoney);

			seaInsuranceDAO.add((SeaInsurance) insurance);
		}


		staff.setResult(staff.getResult()+1);
		this.staffDAO.update(staff);
		return insurance;
	}

	public int setInsuranceId() {
		int insuranceId = 1;
		while (true) {
			if (insuranceDAO.get(insuranceId) == null) {
				return insuranceId;
			} else {
				insuranceId++;
			}
		}
	}

	public ArrayList<Insurance> getInsuranceDAO() {

			return this.insuranceDAO.getInsuranceList();
	}

	public int computeAuthorizeCount() {
		int count = 0;
		if (insuranceDAO instanceof DBInsuranceDAO) {
			for (Insurance insurance : ((DBInsuranceDAO) insuranceDAO).getInsuranceList()) {
				if (insurance.isAuthorization()) {
					count++;
				}
			}
		}
		return count;
	}

	public int computeNotAuthorizeCount() {
		int count = 0;
		if (insuranceDAO instanceof DBInsuranceDAO) {
			for (Insurance insurance : ((DBInsuranceDAO) insuranceDAO).getInsuranceList()) {
				if (!insurance.isAuthorization()) {
					count++;
				}
			}
		}
		return count;
	}


	public int computeTotalSize() {
		return this.insuranceDAO.getSize();
	}
}
