package Customer;

public class House {

  private enum type {
    apartment, //아파트
    housing, //주택
    officetel //오피스텔
  }
  private int price;

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }



}
