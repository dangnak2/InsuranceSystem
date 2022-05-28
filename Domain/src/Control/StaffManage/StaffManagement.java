package Control.StaffManage;

import Staff.*;
import Staff.Staff.Department;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StaffManagement {
    private StaffList staffList;

    public StaffManagement(StaffList staffList) {
        this.staffList = staffList;
    }

    public ArrayList<Staff> getTotalStaff() {
        ArrayList<Staff> totalStaffList = new ArrayList<>();
        if (this.staffList instanceof StaffListImpl) {
            totalStaffList = ((StaffListImpl) this.staffList).getStaffList();
        }
        return totalStaffList;
    }

    public void updateDepartment(int staffId, Department department) {
        Staff staff = this.staffList.get(staffId);
        staff.setDepartment(department);
        this.staffList.update(staff);
    }

    public void fireStaff(int staffId) {
        this.staffList.delete(staffId);
    }

    //회원 가입할 때 사원번호를 발급 해야할지 여기서 발급을 해야되는지 고민해봐야함
    public int issueStaffNum(String staffName, Department department) {
        int staffNum;

        return 0;
    }

    public void calculateSalary(int staffId) {
        Staff staff = this.staffList.get(staffId);

        Date today = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(today);

        Calendar serviceDay = Calendar.getInstance();
        serviceDay.setTime(staff.getJoinDate());

        int count = 0;
        while (!serviceDay.after(calendarToday)) {
            serviceDay.add(Calendar.DATE, 1);
            count++;
        }

//        최종 월급 = 기본 월급(basicSalary) + 근무일수(count) / 365 * x + 판매 실적(result) * y

    }




}
