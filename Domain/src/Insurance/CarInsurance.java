package Insurance;

public class CarInsurance extends Insurance {

  private int humanDamageBasicMoney;
  private int carDamageBasicMoney;

  public CarInsurance() {
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
