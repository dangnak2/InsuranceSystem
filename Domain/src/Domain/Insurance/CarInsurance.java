package Domain.Insurance;

public class CarInsurance extends Insurance {

  private int humanDamageBasicMoney;
  private int carDamageBasicMoney;
  private int carInsuranceId;

  public CarInsurance() {
  }

  public int getCarInsuranceId() {
    return carInsuranceId;
  }

  public void setCarInsuranceId(int carInsuranceId) {
    this.carInsuranceId = carInsuranceId;
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
