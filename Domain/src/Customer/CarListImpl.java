//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

import DB.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarListImpl extends DBConnector implements CarList {
  ArrayList<Car> carList;

  public CarListImpl() {
    this.carList = new ArrayList<Car>();
    super.getConnection();
    this.carList = this.getCarlist();
  }

  public ArrayList<Car> getCarlist() {
    String query = "select * from Car;";
    ResultSet rs = super.retreive(query);
    ArrayList<Car> cars = new ArrayList<>();

    try {
      while(rs.next()) {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setCustomerId(rs.getInt("customer_id"));
        car.setType(Car.Type.valueOf(rs.getString("type")));
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
    String query = "insert into car values (" + car.getId() + ","
        + car.getCustomerId() + ",'" + car.getType()+"',"+ car.getCarNum() +"," + car.getYear() + ",'"
        + car.getDisplacement() + ",'" + car.getPrice() + ");";
    if(super.create(query)){
      this.carList = getCarlist();
      return true;
    }
      return false;
  }
  @Override
  public Car get(int id) {
    for (Car car : carList) {
      if (car.getId() == id) {
        return car;
      }
    }
    return null;
  }

  @Override
  public boolean update(Car car) {
    String query = "update car set customer id = " + car.getCustomerId()
        + ", type = '" + car.getType() + "', carNum = "
        + car.getCarNum() + "', year = " + car.getYear()
        + ", displacement = " + car.getDisplacement() + ", price = " + car.getPrice()
        + " where id =" + car.getId();
    if(super.update(query)){
      this.carList = getCarlist();
      return true;
    }
    return false;
  }

  @Override
  public int getSize() {
    return this.carList.size();
  }


  @Override
  public boolean delete(int id) {

    String query = "delete from car where id=" + id;
    super.delete(query);

    this.carList = this.getCarlist();
    return true;

  }
}