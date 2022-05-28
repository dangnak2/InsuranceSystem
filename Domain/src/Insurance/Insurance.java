package Insurance;

import Contract.Compensation;
import java.util.Date;

public abstract class Insurance {

  private int id;
  private String name;
  private String explanation;
  private int premium;
  private int basicConpensation;
  public boolean authorization;
  private Date authorizationDate;
  private Date createdDate;
  private Date modifiedDate;

  public Insurance() {

  }




  public int getBasicConpensation() {
    return basicConpensation;
  }

  public void setBasicConpensation(int basicConpensation) {
    this.basicConpensation = basicConpensation;
  }

  public Date getAuthorizationDate() {
    return authorizationDate;
  }

  public void setAuthorizationDate(Date authorizationDate) {
    this.authorizationDate = authorizationDate;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPremium() {
    return premium;
  }

  public void setPremium(int premium) {
    this.premium = premium;
  }


  public boolean isAuthorization() {
    return authorization;
  }

  public void setAuthorization(boolean authorization) {
    this.authorization = authorization;
  }

}
