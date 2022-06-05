package Controller.StaffService;

import DAO.StaffDAO.StaffDAO;
import DAO.StaffDAO.DBStaffDAO;
import Domain.Staff.Staff;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StaffService {
    private StaffDAO staffDAO;

    public StaffService(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public ArrayList<Staff> getTotalStaff() {
        return this.staffDAO.getStaffList();
    }

    public Staff login(int staffId, String staffPw) {
        Staff loginStaff = staffDAO.get(staffId);
        if (loginStaff != null) {
            if (staffPw.equals(loginStaff.getPassword())) {
                return loginStaff;
            }
        }
        return null;
    }



    public Staff createStaff(int staffId, String staffPw, String staffName, String staffSsn, int staffGender, String staffEmail, String staffPhone, int department, int position) {
        Staff createdStaff = new Staff();
        Date date = new Date();

        if (department >= 1 && department <= 5) {
            createdStaff.setDepartment(Staff.Department.values()[department - 1]);
        } else {
            return null;
        }

        if (position >= 1 && position <= 6) {
            createdStaff.setPosition(Staff.Position.values()[position - 1]);
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

        createdStaff.setId(this.staffDAO.getSize() + 1);
        if (this.staffDAO.get(staffId) != null) {
            return null;
        }
        createdStaff.setId(staffId);
        createdStaff.setPassword(staffPw);

        staffDAO.add(createdStaff);


        return createdStaff;
    }


    public Staff getStaff(int staffId) {
        if (this.staffDAO instanceof DBStaffDAO) {
            for (Staff staff : ((DBStaffDAO) this.staffDAO).getStaffList()) {
                if (staffId == staff.getId()) {
                    return staff;
                }
            }
        }
        return null;
    }

    public void addResult(Staff staff) {
        staff.setResult(staff.getResult() + 1);

        this.staffDAO.update(staff);
    }

    public boolean updateDepartment(int staffId, int department) {
        Staff staff = this.staffDAO.get(staffId);
        if (staff == null) {
            return false;
        }
        staff.setDepartment(Staff.Department.values()[department-1]);
        this.staffDAO.update(staff);
        return true;
    }

    public void fireStaff(int staffId) {
        this.staffDAO.delete(staffId);
    }

    public boolean calculateSalary(int staffId, Staff loginStaff) {
        Staff staff = this.staffDAO.get(staffId);

        int workDate = this.calculateWorkDate(staffId);
        int totalSalary = staff.getBasicSalary() + ((workDate/ 365) * 100000) + (staff.getResult() * 50000);

        staff.setTotalSalary(totalSalary);
        loginStaff.setResult(staff.getResult()+1);
        this.staffDAO.update(loginStaff);

        if (this.staffDAO.update(staff)) {
            return true;
        } else {
            return false;
        }


//        최종 월급 = 기본 월급(basicSalary) + 근무일수(count) / 365 * x + 판매 실적(result) * y

    }


    public int calculateWorkDate(int staffId) {
        Staff staff = this.staffDAO.get(staffId);

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

    public boolean changePosition(Staff staff, int position, Staff loginStaff) {
        staff.setPosition(Staff.Position.values()[position - 1]);
        if (this.staffDAO.update(staff)) {
            this.calculateSalary(staff.getId(), loginStaff);
            return true;
        }
        return false;
    }



}
