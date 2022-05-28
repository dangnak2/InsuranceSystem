package Insurance;

public class FireInsurance extends Insurance{

  private int housingPrice;
  private int surroundingDamage; //주변 피해 정도
  private int humanDamage; //인명 피해 정도
  private int buildingDamage; //건물 피해 정도
  public enum buildingType {
    apartment, //아파트
    housing, //주택
    officetel //오피스텔
    }
  private buildingType buildingType;

  private final int MONEY_BASIS_SURROUNGDING_DAMAGE = 1000000;
  private final int MONEY_BASIS_HUMAN_DAMAGE = 1000000;
  private final int MONEY_BASIS_BUILDING_DAMAGE = 20000000;

  public int getMONEY_BASIS_SURROUNGDING_DAMAGE() {
    return MONEY_BASIS_SURROUNGDING_DAMAGE;
  }

  public int getMONEY_BASIS_HUMAN_DAMAGE() {
    return MONEY_BASIS_HUMAN_DAMAGE;
  }

  public int getMONEY_BASIS_BUILDING_DAMAGE() {
    return MONEY_BASIS_BUILDING_DAMAGE;
  }

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

  public int getHousingPrice() {
    return housingPrice;
  }

  public void setHousingPrice(int housingPrice) {
    this.housingPrice = housingPrice;
  }

  public buildingType getBuildingType(){
    return buildingType;
  }

}
