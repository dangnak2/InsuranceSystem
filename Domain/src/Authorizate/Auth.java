package Authorizate;

import Domain.Staff.*;


public class Auth {

    private StaffList staffList;

    public Auth(StaffList staffList) {
        this.staffList = staffList;
    }


    public Staff login(int staffId, String staffPw) {
        Staff loginStaff = staffList.get(staffId);
        if (loginStaff != null) {
            if (staffPw.equals(loginStaff.getPassword())) {
                return loginStaff;
            }
        }
        return null;
    }
}
