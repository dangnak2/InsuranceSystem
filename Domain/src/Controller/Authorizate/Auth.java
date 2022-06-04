package Controller.Authorizate;

import Domain.Staff.*;
import DAO.StaffDAO.StaffDAO;


public class Auth {

    private StaffDAO staffRepository;

    public Auth(StaffDAO staffDAO) {
        this.staffRepository = staffDAO;
    }


    public Staff login(int staffId, String staffPw) {
        Staff loginStaff = staffRepository.get(staffId);
        if (loginStaff != null) {
            if (staffPw.equals(loginStaff.getPassword())) {
                return loginStaff;
            }
        }
        return null;
    }
}
