
package DAO.CustomerDAO;

import Domain.Customer.Ship;

import java.util.ArrayList;

public interface ShipDAO {
  boolean add(Ship ship);

  boolean delete(int id);

  Ship get(int id);

  boolean update(Ship ship);

  int getSize();

  public ArrayList<Ship> getShipList();
}
