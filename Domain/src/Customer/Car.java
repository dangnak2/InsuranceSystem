
package Customer;

public class Car {
  private int carNum;
  private int year;
  private int displacement;
  private int price;
  private int customerId;

  public enum Type {
    Domestic,
    Overseas;

  }


  private Type type;

  public Car() {
  }


  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getCarNum() {
    return this.carNum;
  }

  public void setCarNum(int carNum) {
    this.carNum = carNum;
  }

  public int getYear() {
    return this.year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getDisplacement() {
    return this.displacement;
  }

  public void setDisplacement(int displacement) {
    this.displacement = displacement;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


}
