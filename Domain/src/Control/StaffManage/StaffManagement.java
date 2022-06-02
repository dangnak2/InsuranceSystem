package Control.StaffManage;

import Staff.*;
import Staff.Staff.Department;
import Staff.Staff.Position;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public ArrayList<Staff> getDepartmentStaff(int department) {
        ArrayList<Staff> departmentStaffList = new ArrayList<>();
        if (this.staffList instanceof StaffListImpl) {
            for (Staff staff : ((StaffListImpl) this.staffList).getStaffList()) {
                if (Department.values()[department-1] == staff.getDepartment()) {
                    departmentStaffList.add(staff);
                }
            }
        }
        return departmentStaffList;
    }

    public Staff createStaff(int staffId, String staffPw, String staffName, String staffSsn, int staffGender, String staffEmail, String staffPhone, int department, int position) {
        Staff createdStaff = new Staff();
        Date date = new Date();

        if (department >= 1 && department <= 5) {
            createdStaff.setDepartment(Department.values()[department - 1]);
        } else {
            return null;
        }

        if (position >= 1 && position <= 6) {
            createdStaff.setPosition(Position.values()[position - 1]);
        } else {
            return null;
        }

        createdStaff.setName(staffName);
        createdStaff.setSSN(staffSsn);
        if (staffGender == 1) {
            createdStaff.setGender(true);
        } else if (staffGender == 2) {
            createdStaff.setGender(false);
        }
        createdStaff.setEmail(staffEmail);
        createdStaff.setPhoneNum(staffPhone);
        createdStaff.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
        String[] staffBirth = createdStaff.getSSN().split("-");

        createdStaff.setId(this.staffList.getSize() + 1);
        if (this.staffList.get(staffId) != null) {
            return null;
        }
        createdStaff.setId(staffId);
        createdStaff.setPassword(staffPw);

        staffList.add(createdStaff);


        return createdStaff;
    }

    public Staff getStaff(int staffId) {
        if (this.staffList instanceof StaffListImpl) {
            for (Staff staff : ((StaffListImpl) this.staffList).getStaffList()) {
                if (staffId == staff.getId()) {
                    return staff;
                }
            }
        }
        return null;
    }

    public boolean updateDepartment(int staffId, int department) {
        Staff staff = this.staffList.get(staffId);
        if (staff == null) {
            return false;
        }
        staff.setDepartment(Department.values()[department-1]);
        this.staffList.update(staff);
        return true;
    }

    public void fireStaff(int staffId) {
        this.staffList.delete(staffId);
    }

    //회원 가입할 때 사원번호를 발급 해야할지 여기서 발급을 해야되는지 고민해봐야함
    public int issueStaffNum(String staffName, Department department) {
        int staffNum;

        return 0;
    }

    public int calculateWorkDate(int staffId) {
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

        return count;

//        최종 월급 = 기본 월급(basicSalary) + 근무일수(count) / 365 * x + 판매 실적(result) * y

    }

    public void changePosition(Staff staff, int position, Staff loginStaff) {
        staff.setPosition(Position.values()[position - 1]);
        this.calculateSalary(staff.getId(), loginStaff);
    }

    public void calculateSalary(int staffId, Staff loginStaff) {
        Staff staff = this.staffList.get(staffId);

        int workDate = this.calculateWorkDate(staffId);
        int totalSalary = staff.getBasicSalary() + ((workDate/ 365) * 100000) + (staff.getResult() * 50000);

        staff.setTotalSalary(totalSalary);
        loginStaff.setResult(staff.getResult()+1);
        this.staffList.update(staff);

//        최종 월급 = 기본 월급(basicSalary) + 근무일수(count) / 365 * x + 판매 실적(result) * y

    }




}
