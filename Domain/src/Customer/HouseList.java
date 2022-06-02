
package Customer;

public interface HouseList {
  boolean add(House house);

  boolean delete(int customerId);

  House get(int customerId);

  boolean update(House house);

  int getSize();
}
