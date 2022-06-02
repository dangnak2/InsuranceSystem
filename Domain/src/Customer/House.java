//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Customer;

public class House {
  public enum HouseType {
    apartment,
    housing,
    officetels
  }
  private HouseType houseType;
  private int price;
  private int customer_id;
  private int id;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


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

  public int getCustomer_id() {
    return this.customer_id;
  }

  public void setCustomerId(int customer_id) {
    this.customer_id = customer_id;
  }


}
