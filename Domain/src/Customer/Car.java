package Customer;

public class Car {
  private enum type {
    Domestic, //국내
    Overseas //해외
  }
  private int carNum;
  private int year;
  private int displacement; //배기량
  private int price;

  public int getCarNum() {
    return carNum;
  }

  public void setCarNum(int carNum) {
    this.carNum = carNum;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getDisplacement() {
    return displacement;
  }

  public void setDisplacement(int displacement) {
    this.displacement = displacement;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


}
