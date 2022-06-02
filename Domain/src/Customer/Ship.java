package Customer;

public class Ship {
  public enum ShipType{
    General, // 일반 화물
    Container // 컨테이너선
  }
  private int shipNum;
  private int year;
  private int price;
  private ShipType shipType;

  public int getShipNum() {
    return shipNum;
  }

  public void setShipNum(int shipNum) {
    this.shipNum = shipNum;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ShipType getShipType() {
    return shipType;
  }

  public void setShipType(ShipType shipType) {
    this.shipType = shipType;
  }
}
