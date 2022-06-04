//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Domain.Customer;

import DAO.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        house.setId(rs.getInt("id"));
        house.setCustomerId(rs.getInt("customer_id"));
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
    String query = "insert into house values (" + house.getId() + ","
        + house.getCustomer_id() + "," + house.getPrice() +",'"+ house.getHouseType() +"');";

    if(super.create(query)) {
      this.houseList = getHouseList();
      return true;
    }
    return false;
  }
  @Override
  public House get(int id) {
    for (House house : houseList) {
      if (house.getId() == id) {
        return house;
      }
    }
    return null;
  }

  @Override
  public boolean update(House house) {
    String query = "update house set customer_id = "
        + house.getCustomer_id() +", price = " + house.getPrice()
        + " , houseType = '" + house.getHouseType() + "' where id = " + house.getId();
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
  public boolean delete(int id) {

    String query = "delete from house where id=" + id;

    if(super.delete(query)) {
      this.houseList = this.getHouseList();
      return true;
    }
    return false;
  }
}
