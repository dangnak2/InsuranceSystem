package Domain.Customer;

import java.util.ArrayList;

public interface CarList {
  boolean add(Car car);

  boolean delete(int id);

  Car get(int id);

  boolean update(Car car);

  int getSize();

  public ArrayList<Car> getCarList();
}
