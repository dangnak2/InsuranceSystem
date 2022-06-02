//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

import DB.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class HouseListImpl extends DBConnector implements HouseList {
  ArrayList<House> houseList;

  public HouseListImpl() {
    houseList = new ArrayList<House>();
    super.getConnection();
    this.houseList = this.getHouseList();
  }

  public ArrayList<House> getHouseList() {
    String query = "select * from house;";
    ResultSet rs = super.retreive(query);

    try {
      while(rs.next()) {
        House house = new House();
        house.setCustomer_id(rs.getInt("customer_id"));
        house.setPrice(rs.getInt("price"));
        house.setHouseType(House.HouseType.valueOf(rs.getString("houseType")));
        this.houseList.add(house);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return this.houseList;
  }

  public boolean add(House house) {
    String query = "insert into house values ("
        + house.getCustomer_id() + "," + house.getPrice() +",'"+ house.getHouseType() +"');";

    if(super.create(query)) {
      this.houseList = getHouseList();
      return true;
    }
    return false;
  }
  @Override
  public House get(int customer_Id) {
    for (House house : houseList) {
      if (house.getCustomer_id() == customer_Id) {
        return house;
      }
    }
    return null;
  }

  @Override
  public boolean update(House house) {
    String query = "update house set customer_id = "
        + house.getCustomer_id() +", price = " + house.getPrice()
        + " , houseType = '" + house.getHouseType() + "';";
    if(super.update(query)){
      this.houseList = getHouseList();
      return true;
    }
    return false;
  }

  @Override
  public int getSize() {
    return this.houseList.size();
  }


  @Override
  public boolean delete(int customer_id) {

    String query = "delete from house where customer_id=" + customer_id;

    if(super.delete(query)) {
      this.houseList = this.getHouseList();
      return true;
    }
    return false;
  }
}