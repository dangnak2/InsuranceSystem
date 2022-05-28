package Insurance;

public class SeaInsurance extends Insurance {


  private int shipPrice;
  private int generalDamage; //제반 손해 정도
  private int revenueDamage; //수익 손해 정도

  private final int MONEY_BASIS_GENERAL_DAMAGE = 100000000;
  private final int MONEY_BASIS_REVENUE_DAMAGE = 100000000;

  public int getMONEY_BASIS_GENERAL_DAMAGE() {
    return MONEY_BASIS_GENERAL_DAMAGE;
  }

  public int getMONEY_BASIS_REVENUE_DAMAGE() {
    return MONEY_BASIS_REVENUE_DAMAGE;
  }

  public int getGeneralDamage() {
    return generalDamage;
  }

  public void setGeneralDamage(int generalDamage) {
    this.generalDamage = generalDamage;
  }

  public int getRevenueDamage() {
    return revenueDamage;
  }

  public void setRevenueDamage(int revenueDamage) {
    this.revenueDamage = revenueDamage;
  }

  public int getShipPrice() {
    return shipPrice;
  }

  public void setShipPrice(int shipPrice) {
    this.shipPrice = shipPrice;
  }
}
