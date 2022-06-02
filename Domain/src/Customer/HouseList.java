
package Customer;

public interface HouseList {
  boolean add(House house);

  boolean delete(int id);

  House get(int id);

  boolean update(House house);

  int getSize();
}
