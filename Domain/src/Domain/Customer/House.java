//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Domain.Customer;

public class House {
  public enum HouseType {
    apartment,
    housing,
    officetels
  }
  private HouseType houseType;
  private int price;
  private int customerId;

  public House() {
  }

  public House.HouseType getHouseType() {
    return this.houseType;
  }

  public void setHouseType(House.HouseType houseType) {
    this.houseType = houseType;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(int customer_id) {
    this.customerId = customer_id;
  }


}
