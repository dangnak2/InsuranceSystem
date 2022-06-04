package DAO.CustomerDAO;

import DAO.DBConnector.DBConnectorDAO;
import Domain.Customer.Ship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBShipDAO extends DBConnectorDAO implements ShipDAO {

  private ArrayList<Ship> shipList;

  public DBShipDAO() {
    this.shipList = new ArrayList<>();
    super.getConnection();
    this.shipList = this.getShipList();
  }

  public ArrayList<Ship> getShipList() {
    String query = "select * from ship;";
    ResultSet rs = super.retrieve(query);
    ArrayList<Ship> ships = new ArrayList<Ship>();
    try {
      while (rs.next()) {
        Ship ship = new Ship();
        ship.setCustomerId(rs.getInt("customer_id"));
        ship.setShipNum(rs.getInt("shipNum"));
        ship.setYear(rs.getInt("year"));
        ship.setPrice(rs.getInt("price"));
        ship.setShipType(Ship.ShipType.valueOf(rs.getString("shipType")));



        ships.add(ship);
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return ships;
  }


  @Override
  public boolean add(Ship ship) {
    String query = "insert into ship values ("
        + ship.getCustomerId() + "," + ship.getShipNum() + "," + ship.getYear()
        + "," + ship.getPrice() + ",'" + ship.getShipType() + "');";
    if(super.create(query)){
      this.shipList = getShipList();
      return true;
    }
    return false;
  }

  @Override
  public Ship get(int id) {
    for (Ship ship : shipList) {
      if (ship.getCustomerId() == id) {
        return ship;
      }
    }
    return null;
  }

  @Override
  public boolean update(Ship ship) {
    String query = "update ship set  shipNum = "
        + ship.getShipNum() + ", year = " + ship.getYear()
        + ", price = " + ship.getPrice() + ", shipType = '" + ship.getShipType()
        + "' where customer_id = " + ship.getCustomerId();
    if(super.update(query)){
      this.shipList = getShipList();
      return true;
    }
    return false;
  }

  @Override
  public int getSize() {
    return this.shipList.size();
  }


  @Override
  public boolean delete(int id) {

    String query = "delete from ship where id=" + id;
    if(super.delete(query)) {
      this.shipList = this.getShipList();
      return true;
    }

    return false;
  }
}
