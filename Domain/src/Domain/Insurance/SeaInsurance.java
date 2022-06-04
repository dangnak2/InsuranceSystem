package Domain.Insurance;

public class SeaInsurance extends Insurance {

  private int seaInsurance_id;
  private int generalDamageBasicMoney; //제반 손해 정도
  private int revenueDamageBasicMoney; //수익 손해 정도

  public int getSeeInsurance_id() {
    return seaInsurance_id;
  }

  public void setSeeInsurance_id(int seeInsurance_id) {
    this.seaInsurance_id = seeInsurance_id;
  }

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
