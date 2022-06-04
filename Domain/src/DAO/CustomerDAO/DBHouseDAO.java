//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DAO.CustomerDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Customer.House;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHouseDAO extends DBConnectorDAO implements HouseDAO {
  ArrayList<House> houseList;

  public DBHouseDAO() {
    houseList = new ArrayList<House>();
    super.getConnection();
    this.houseList = this.getHouseList();
  }

  public ArrayList<House> getHouseList() {
    String query = "select * from house;";
    ResultSet rs = super.retrieve(query);

    try {
      while(rs.next()) {
        House house = new House();
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
    String query = "insert into house values ("+ house.getCustomerId() + "," + house.getPrice() +",'"+ house.getHouseType() +"');";

    if(super.create(query)) {
      this.houseList = getHouseList();
      return true;
    }
    return false;
  }
  @Override
  public House get(int customerId) {
    for (House house : houseList) {
      if (house.getCustomerId() == customerId) {
        return house;
      }
    }
    return null;
  }

  @Override
  public boolean update(House house) {
    String query = "update house set  price = " + house.getPrice()
        + " , houseType = '" + house.getHouseType() + "' where customer_id = " + house.getCustomerId();
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

    String query = "delete from house where customer_id=" + id;

    if(super.delete(query)) {
      this.houseList = this.getHouseList();
      return true;
    }
    return false;
  }
}
