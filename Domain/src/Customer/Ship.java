//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

public class Ship {
  private int shipNum;
  private int year;
  private int price;
  public enum ShipType {
    General,
    Container;
  }
  private ShipType shipType;
  private int customer_id;

  public Ship() {
  }

  public int getShipNum() {
    return this.shipNum;
  }

  public void setShipNum(int shipNum) {
    this.shipNum = shipNum;
  }

  public int getYear() {
    return this.year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ShipType getShipType() {
    return this.shipType;
  }

  public void setShipType(Ship.ShipType shipType) {
    this.shipType = shipType;
  }

  public int getCustomer_id() {
    return this.customer_id;
  }

  public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
  }


}
