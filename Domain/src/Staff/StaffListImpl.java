package Staff;

import DB.DBConnector;
import Insurance.Insurance;

import java.util.ArrayList;

public class StaffListImpl extends DBConnector implements StaffList {

    ArrayList<Staff> staffList;

    public StaffListImpl() {
        this.staffList = new ArrayList<>();
    }

    public ArrayList<Staff> getStaffList() {
        return this.staffList;
    }

    @Override
    public boolean add(Staff staff) {
        if (this.staffList.add(staff)) {
            String query = "INSERT INTO staff VALUES ("
                +staff.getId()+",'"+staff.getEmail()+"','"+staff.getName()
                +"','"+staff.getSSN()+"',"+staff.isGender()+",'"+staff.getPassword()+"','"
                +staff.getPhoneNum()+"','"+staff.getJoinDate()+"','"+staff.getDepartment()+"',"
                +staff.getBasicSalary()+","+staff.getTotalSalary()+","+staff.getResult()
                +");";
            super.getConnection();
            super.add(query);
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(int staffId) {
        if (this.staffList.remove(this.get(staffId))) {
            return true;
        } else
            return false;
    }

    @Override
    public Staff get(int staffId) {
        for (Staff staff : staffList) {
            if (staff.getId() == staffId) {
                return staff;
            }
        }
        return null;
    }

    @Override
    public void update(Staff staff) {
        int index = this.staffList.indexOf(staff);
        this.staffList.set(index, staff);
    }
}
