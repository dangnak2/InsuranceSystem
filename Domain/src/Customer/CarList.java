package Customer;

public interface CarList {
  boolean add(Car car);

  boolean delete(int customerId);

  Car get(int customerId);

  boolean update(Car car);

  int getSize();
}
