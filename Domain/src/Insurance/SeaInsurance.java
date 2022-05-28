package Insurance;

public class SeaInsurance extends Insurance {


  private int generalDamageBasicMoney; //제반 손해 정도
  private int revenueDamageBasicMoney; //수익 손해 정도

  public int getGeneralDamageBasicMoney() {
    return generalDamageBasicMoney;
  }

  public void setGeneralDamageBasicMoney(int generalDamageBasicMoney) {
    this.generalDamageBasicMoney = generalDamageBasicMoney;
  }

  public int getRevenueDamageBasicMoney() {
    return revenueDamageBasicMoney;
  }

  public void setRevenueDamageBasicMoney(int revenueDamageBasicMoney) {
    this.revenueDamageBasicMoney = revenueDamageBasicMoney;
  }
}
