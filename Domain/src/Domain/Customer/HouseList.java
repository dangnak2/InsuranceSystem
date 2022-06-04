
package Domain.Customer;

import java.util.ArrayList;

public interface HouseList {
  boolean add(House house);

  boolean delete(int id);

  House get(int id);

  boolean update(House house);

  int getSize();

  public ArrayList<House> getHouseList();
}
