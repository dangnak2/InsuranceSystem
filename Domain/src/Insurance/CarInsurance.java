package Insurance;

public class CarInsurance extends Insurance {

  private int humanDamageBasicMoney;
  private int carDamageBasicMoney;
  private int carInsurance_id;

  public CarInsurance() {
  }

  public int getCarInsurance_id() {
    return carInsurance_id;
  }

  public void setCarInsurance_id(int carInsurance_id) {
    this.carInsurance_id = carInsurance_id;
  }

  public int getInsurance_id() {
    return super.getId();
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
