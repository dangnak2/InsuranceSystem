package Customer;

public class House {

  public enum HouseType {
    apartment, //아파트
    housing, //주택
    officetels //오피스텔
  }


  private HouseType houseType;
  private int price;

  public HouseType getHouseType() {
    return houseType;
  }

  public void setHouseType(HouseType houseType) {
    this.houseType = houseType;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }



}
