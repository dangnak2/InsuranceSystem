package Insurance;

public class CarInsurance extends Insurance {

  private int carPrice;
  private int accidentDegree; //사고난 정도
  private int humanDamage;
  private int errorRate; //과실 비율
  private boolean isDomestic;

  public int getAccidentDegree() {
    return accidentDegree;
  }

  public void setAccidentDegree(int accidentDegree) {
    this.accidentDegree = accidentDegree;
  }

  public int getHumanDamage() {
    return humanDamage;
  }

  public void setHumanDamage(int humanDamage) {
    this.humanDamage = humanDamage;
  }

  public int getErrorRate() {
    return errorRate;
  }

  public void setErrorRate(int errorRate) {
    this.errorRate = errorRate;
  }

  public int getCarPrice() {
    return carPrice;
  }

  public void setCarPrice(int carPrice) {
    this.carPrice = carPrice;
  }

  public boolean isDomestic() {
    return isDomestic;
  }

  public void setDomestic(boolean domestic) {
    isDomestic = domestic;
  }
}
