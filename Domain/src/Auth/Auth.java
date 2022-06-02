package Auth;

import Control.Design.Design;
import Staff.*;
import Staff.Staff.Department;
import Staff.Staff.Position;
import Staff.StaffListImpl;

import java.util.Date;


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
