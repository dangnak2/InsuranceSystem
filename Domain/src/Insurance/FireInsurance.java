package Insurance;

public class FireInsurance extends Insurance{


  private int fireInsurance_id;
  private int surroundingDamageBasicMoney;
  private int humanDamageBasicMoney;
  private int buildingDamageBasicMoney;

  public int getFireInsurance_id() {
    return fireInsurance_id;
  }

  public void setFireInsurance_id(int fireInsurance_id) {
    this.fireInsurance_id = fireInsurance_id;
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
