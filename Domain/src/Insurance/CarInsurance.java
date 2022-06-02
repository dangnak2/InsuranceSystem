package Insurance;

public class CarInsurance extends Insurance {

  private int humanDamageBasicMoney;
  private int carDamageBasicMoney;

  public CarInsurance() {
    this.setType(Type.Car);

  }

  public int getHumanDamageBasicMoney() {
    return humanDamageBasicMoney;
  }

  public void setHumanDamageBasicMoney(int humanDamageBasicMoney) {
    this.humanDamageBasicMoney = humanDamageBasicMoney;
  }

  public int getCarDamageBasicMoney() {
    return carDamageBasicMoney;
  }

  public void setCarDamageBasicMoney(int carDamageBasicMoney) {
    this.carDamageBasicMoney = carDamageBasicMoney;
  }
}
