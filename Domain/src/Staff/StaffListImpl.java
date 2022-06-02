package Staff;

import Contract.Contract;
import DB.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffListImpl extends DBConnector implements StaffList {

    ArrayList<Staff> staffList;

    public StaffListImpl() {
        this.staffList = new ArrayList<>();
        super.getConnection();
        this.staffList = this.getStaffList();
    }

    public ArrayList<Staff> getStaffList() {
        String query = "select * from staff;";
        ResultSet rs = super.retreive(query);
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        try {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setEmail(rs.getString("email"));
                staff.setName(rs.getString("name"));
                staff.setSSN(rs.getString("SSN"));
                staff.setGender(rs.getBoolean("gender"));
                staff.setPassword(rs.getString("password"));
                staff.setPhoneNum(rs.getString("phoneNum"));
                staff.setJoinDate(rs.getDate("joinDate"));
                staff.setDepartment(Staff.Department.valueOf(rs.getString("department")));
                staff.setBasicSalary(rs.getInt("basicSalary"));
                staff.setTotalSalary(rs.getInt("totalSalary"));
                staff.setResult(rs.getInt("result"));
                staff.setPosition(Staff.Position.valueOf(rs.getString("position")));

                staffList.add(staff);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }


    @Override
    public boolean add(Staff staff) {
        String query = "insert into staff values ("
            + staff.getId() + ",'" + staff.getEmail() + "','" + staff.getName()
            + "','" + staff.getSSN() + "'," + staff.isGender() + ",'" + staff.getPassword() + "','"
            + staff.getPhoneNum() + "','" + staff.getJoinDate() + "','" + staff.getDepartment() + "',"
            + staff.getBasicSalary() + "," + staff.getTotalSalary() + "," + staff.getResult() + ",'" + staff.getPosition() +"'"
            + ");";
        if(super.create(query)) {
            this.staffList = getStaffList();
            return true;
        }
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
    public boolean update(Staff staff) {
        String query = "update staff set email = '"
            + staff.getEmail() + "', name = '" + staff.getName() + "', SSN = '" + staff.getSSN() + "', gender = " + staff.isGender()
            + ", password = '" + staff.getPassword() + "', phoneNum = '" + staff.getPhoneNum() + "', joinDate = '" + staff.getJoinDate()
            + "', department = '" + staff.getDepartment() + "', basicSalary = " + staff.getBasicSalary() + ", totalSalary = " + staff.getTotalSalary()
            + ", result = " + staff.getResult() + ", position = '" + staff.getPosition() + "'" + " where id = "+staff.getId();

        if(super.update(query)){
            this.staffList = getStaffList();
            return true;
        }

        return false;
    }


    @Override
    public boolean delete(int staffId) {

        String query = "delete from staff where id="+staffId;
        if(super.delete(query)){
            this.staffList = this.getStaffList();
            return true;
        }

        return false;

    }

    @Override
    public int getSize() {
        return this.staffList.size();
    }



}
