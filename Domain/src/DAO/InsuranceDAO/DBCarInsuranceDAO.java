package DAO.InsuranceDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Insurance.CarInsurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCarInsuranceDAO extends DBConnectorDAO implements CarInsuranceDAO {


    public DBCarInsuranceDAO() {
        this.getConnection();
    }

    public ArrayList<CarInsurance> getCarInsuranceList() {
        ArrayList<CarInsurance> carInsuranceList = new ArrayList<>();
        String query = "select * from carInsurance;";
        ResultSet rs = super.retrieve(query);
        try {
            while (rs.next()) {
                CarInsurance carInsurance = new CarInsurance();

                carInsurance.setId(rs.getInt("carInsurance_id"));
                carInsurance.setHumanDamageBasicMoney(rs.getInt("humanDamageBasicMoney"));
                carInsurance.setCarDamageBasicMoney(rs.getInt("carDamageBasicMoney"));

                carInsuranceList.add(carInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carInsuranceList;
    }

    @Override
    public boolean add(CarInsurance carInsurance) {
        String query = "INSERT INTO carInsurance VALUES ("
                + carInsurance.getId() + "," + carInsurance.getHumanDamageBasicMoney()
                + "," + carInsurance.getCarDamageBasicMoney() + ");";
        if (super.create(query)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean delete(int insuranceId) {
        String query = "DELETE FROM carInsurance WHERE carInsurance_id =  " + insuranceId + ";";
        if (super.delete(query)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(CarInsurance carInsurance) {

        String query = "UPDATE carInsurance SET humanDamageBasicMoney = "
                + carInsurance.getHumanDamageBasicMoney()
                + ", carDamageBasicMoney = " + carInsurance.getCarDamageBasicMoney()
                + " WHERE carInsurance_id = " + carInsurance.getId();
        if (super.update(query)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CarInsurance get(int insuranceId) {
        for (CarInsurance carInsurance : getCarInsuranceList()) {
            if (insuranceId == carInsurance.getId()) {
                return carInsurance;
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return getCarInsuranceList().size();
    }
}
