package Insurance;

public class SeaInsurance extends Insurance {

  private int generalDamage; //제반 손해 정도
  private int revenueDamage; //수익 손해 정도

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


}
