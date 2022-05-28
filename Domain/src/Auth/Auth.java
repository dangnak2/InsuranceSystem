package Auth;

import Control.Design.Design;
import Staff.*;
import Staff.Staff.Department;
import Staff.StaffListImpl;

import java.util.Date;


public class Auth {

	public Auth() {

	}

	public Staff createStaff(String name, String ssn, String gender, String email, String phone, String department, StaffListImpl staffList) {
		Staff createdStaff = new Staff();
		Date date = new Date();


		switch (Integer.parseInt(department)) {
			case 1:
				createdStaff.setDepartment(Department.Design);
				break;
			case 2:
				createdStaff.setDepartment(Department.Sales);
				break;
			case 3:
				createdStaff.setDepartment(Department.CompensationManagement);
				break;
		}

		if (createdStaff != null) {
			createdStaff.setName(name);
			createdStaff.setSSN(ssn);
			if (Integer.parseInt(gender) == 1) {
				createdStaff.setGender(true);
			} else if (Integer.parseInt(gender) == 2) {
				createdStaff.setGender(false);
			}
			createdStaff.setEmail(email);
			createdStaff.setPhoneNum(phone);
			createdStaff.setJoinDate(date);
			String[] staffBirth = createdStaff.getSSN().split("-");
			String staffId = createdStaff.getDepartment().ordinal() + staffBirth[0];

			createdStaff.setId(Integer.parseInt(staffId));
			createdStaff.setPassword("1234");

			staffList.add(createdStaff);


		}

		return createdStaff;
	}


	public Staff login(int parseInt, String inputPW, StaffListImpl staffList) {
		Staff loginStaff = staffList.get(parseInt);
		if (loginStaff != null) {
			if (loginStaff.getPassword().equals(inputPW)) {
				return loginStaff;
			}
		}
		return null;
	}
}
