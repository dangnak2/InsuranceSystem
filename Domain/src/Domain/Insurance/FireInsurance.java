package Domain.Insurance;

public class FireInsurance extends Insurance{


  private int fireInsuranceId;
  private int surroundingDamageBasicMoney;
  private int humanDamageBasicMoney;
  private int buildingDamageBasicMoney;

  public int getFireInsuranceId() {
    return fireInsuranceId;
  }

  public void setFireInsuranceId(int fireInsuranceId) {
    this.fireInsuranceId = fireInsuranceId;
  }

  public int getSurroundingDamageBasicMoney() {
    return surroundingDamageBasicMoney;
  }

  public void setSurroundingDamageBasicMoney(int surroundingDamageBasicMoney) {
    this.surroundingDamageBasicMoney = surroundingDamageBasicMoney;
  }

  public int getHumanDamageBasicMoney() {
    return humanDamageBasicMoney;
  }

  public void setHumanDamageBasicMoney(int humanDamageBasicMoney) {
    this.humanDamageBasicMoney = humanDamageBasicMoney;
  }

  public int getBuildingDamageBasicMoney() {
    return buildingDamageBasicMoney;
  }

  public void setBuildingDamageBasicMoney(int buildingDamageBasicMoney) {
    this.buildingDamageBasicMoney = buildingDamageBasicMoney;
  }
}
