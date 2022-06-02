
package Customer;

public interface ShipList {
  boolean add(Ship ship);

  boolean delete(int customerId);

  Ship get(int customerId);

  boolean update(Ship ship);

  int getSize();
}
