package Customer;

import DB.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShipListImpl extends DBConnector implements ShipList {

  ArrayList<Ship> shipList;

  public ShipListImpl() {
    this.shipList = new ArrayList<>();
    super.getConnection();
    this.shipList = this.getShipList();
  }

  public ArrayList<Ship> getShipList() {
    String query = "select * from ship;";
    ResultSet rs = super.retreive(query);
    ArrayList<Ship> ships = new ArrayList<Ship>();
    try {
      while (rs.next()) {
        Ship ship = new Ship();
        ship.setCustomer_id(rs.getInt("customer_id"));
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
        + ship.getCustomer_id() + "," + ship.getShipNum() + "," + ship.getYear()
        + "," + ship.getPrice() + ",'" + ship.getShipType() + "');";
    if(super.create(query)){
      this.shipList = getShipList();
      return true;
    }
    return false;
  }

  @Override
  public Ship get(int customer_Id) {
    for (Ship ship : shipList) {
      if (ship.getCustomer_id() == customer_Id) {
        return ship;
      }
    }
    return null;
  }

  @Override
  public boolean update(Ship ship) {
    String query = "update ship set shipNum = "
        + ship.getShipNum() + ", year = " + ship.getYear()
        + ", price = " + ship.getPrice() + ", shipType = '" + ship.getShipType()
        + "' where customer_id = " + ship.getCustomer_id();
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
  public boolean delete(int customer_id) {

    String query = "delete from ship where customer_id=" + customer_id;
    if(super.delete(query)) {
      this.shipList = this.getShipList();
      return true;
    }

    return false;
  }
}