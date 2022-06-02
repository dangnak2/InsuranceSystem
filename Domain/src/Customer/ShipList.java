
package Customer;

public interface ShipList {
  boolean add(Ship ship);

  boolean delete(int id);

  Ship get(int id);

  boolean update(Ship ship);

  int getSize();
}
