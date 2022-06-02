package Insurance;

public class FireInsurance extends Insurance{
  private int surroundingDamageBasicMoney;
  private int humanDamageBasicMoney;
  private int buildingDamageBasicMoney;

  public FireInsurance() {
    this.setType(Type.Fire);
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
