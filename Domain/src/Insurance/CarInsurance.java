package Insurance;

public class CarInsurance extends Insurance {


  private int accidentDegree; //사고난 정도
  private int humanDamage;
  private int errorRate; //과실 비율

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

}
