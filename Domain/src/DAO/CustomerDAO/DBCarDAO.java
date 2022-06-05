//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DAO.CustomerDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Customer.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCarDAO extends DBConnectorDAO implements CarDAO {

    public DBCarDAO() {
        super.getConnection();
    }

    public ArrayList<Car> getCarList() {
        String query = "select * from Car;";
        ResultSet rs = super.retrieve(query);
        ArrayList<Car> cars = new ArrayList<>();

        try {
            while (rs.next()) {
                Car car = new Car();
                car.setCustomerId(rs.getInt("customer_id"));
                car.setCarNum(rs.getInt("carNum"));
                car.setYear(rs.getInt("year"));
                car.setDisplacement(rs.getInt("Displacement"));
                car.setPrice(rs.getInt("price"));
                cars.add(car);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }


        return cars;
    }

    public boolean add(Car car) {
        String query = "insert into car values (" + car.getCustomerId() + ","
                + car.getCarNum() + "," + car.getYear() + ","
                + car.getDisplacement() + "," + car.getPrice() + ");";
        if (super.create(query)) {
            return true;
        }
        return false;
    }

    @Override
    public Car get(int customerId) {
        for (Car car : getCarList()) {
            if (car.getCustomerId() == customerId) {
                return car;
            }
        }
        return null;
    }

    @Override
    public boolean update(Car car) {
        String query = "update car set carNum = "
                + car.getCarNum() + "', year = " + car.getYear()
                + ", displacement = " + car.getDisplacement() + ", price = " + car.getPrice()
                + " where customer_id =" + car.getCustomerId();
        if (super.update(query)) {
            return true;
        }
        return false;
    }

    @Override
    public int getSize() {
        return getCarList().size();
    }


    @Override
    public boolean delete(int id) {

        String query = "delete from car where customer_id=" + id;


        if (super.delete(query)) {
            return true;
        }
        return false;

    }
}
