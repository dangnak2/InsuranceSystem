package Insurance;

public class FireInsurance extends Insurance{
  private int surroundingDamage; //주변 피해 정도
  private enum buildingType {
    apartment, //아파트
    housing, //주택
    officetel //오피스텔
  }
  private int humanDamage; //인명 피해 정도
  private int buildingDamage; //건물 피해 정도

  public int getSurroundingDamage() {
    return surroundingDamage;
  }

  public void setSurroundingDamage(int surroundingDamage) {
    this.surroundingDamage = surroundingDamage;
  }

  public int getHumanDamage() {
    return humanDamage;
  }

  public void setHumanDamage(int humanDamage) {
    this.humanDamage = humanDamage;
  }

  public int getBuildingDamage() {
    return buildingDamage;
  }

  public void setBuildingDamage(int buildingDamage) {
    this.buildingDamage = buildingDamage;
  }
}
