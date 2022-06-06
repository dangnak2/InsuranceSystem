package DAO.InsuranceDAO;


import DAO.DBConnector.DBConnectorDAO;
import Domain.Insurance.CarInsurance;
import Domain.Insurance.FireInsurance;
import Domain.Insurance.Insurance;
import Domain.Insurance.SeaInsurance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBInsuranceDAO extends DBConnectorDAO implements InsuranceDAO {

  private CarInsuranceDAO carInsuranceDAO;
  private FireInsuranceDAO fireInsuranceDAO;
  private SeaInsuranceDAO seaInsuranceDAO;

  public DBInsuranceDAO(CarInsuranceDAO carInsuranceDAO, FireInsuranceDAO fireInsuranceDAO, SeaInsuranceDAO seaInsuranceDAO) {
    this.carInsuranceDAO = carInsuranceDAO;
    this.fireInsuranceDAO = fireInsuranceDAO;
    this.seaInsuranceDAO = seaInsuranceDAO;
    this.getConnection();
  }

  public ArrayList<Insurance> getInsuranceList() {
    ArrayList<Insurance> insuranceList = new ArrayList<>();

    Insurance insurance = null;

    String query = "select * from insurance;";
    ResultSet rs = super.retrieve(query);
    try {
      while (rs.next()) {
        if (rs.getString("type").equals("Fire")) {
          insurance = new FireInsurance();
        } else if (rs.getString("type").equals("Car")) {
          insurance = new CarInsurance();
        } else if (rs.getString("type").equals("Sea")) {
          insurance = new SeaInsurance();
        }

        if (insurance != null) {
          insurance.setId(rs.getInt("id"));
          insurance.setAuthorization(rs.getBoolean("authorization"));
          insurance.setAuthorizedDate(rs.getDate("authorizedDate"));
          insurance.setCreatedDate(rs.getDate("createdDate"));
          insurance.setExplanation(rs.getString("explanation"));
          insurance.setModifiedDate(rs.getDate("modifiedDate"));
          insurance.setPremium(rs.getInt("premium"));
          insurance.setName(rs.getString("name"));
          insurance.setType(Insurance.Type.valueOf(rs.getString("type")));

          if (insurance instanceof FireInsurance) {
            ((FireInsurance) insurance).setBuildingDamageBasicMoney(fireInsuranceDAO.get(insurance.getId()).getBuildingDamageBasicMoney());
            ((FireInsurance) insurance).setHumanDamageBasicMoney(fireInsuranceDAO.get(insurance.getId()).getHumanDamageBasicMoney());
            ((FireInsurance) insurance).setSurroundingDamageBasicMoney(fireInsuranceDAO.get(insurance.getId()).getSurroundingDamageBasicMoney());
          } else if (insurance instanceof CarInsurance) {
            ((CarInsurance) insurance).setCarDamageBasicMoney(carInsuranceDAO.get(insurance.getId()).getCarDamageBasicMoney());
            ((CarInsurance) insurance).setHumanDamageBasicMoney(carInsuranceDAO.get(insurance.getId()).getHumanDamageBasicMoney());
          }else if (insurance instanceof SeaInsurance) {
            ((SeaInsurance) insurance).setGeneralDamageBasicMoney(seaInsuranceDAO.get(insurance.getId()).getGeneralDamageBasicMoney());
            ((SeaInsurance) insurance).setRevenueDamageBasicMoney(seaInsuranceDAO.get(insurance.getId()).getRevenueDamageBasicMoney());
          }

          insuranceList.add(insurance);
        }


      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return insuranceList;

  }

  @Override
  public boolean add(Insurance insurance) {
    String query = "INSERT INTO insurance VALUES ("
            + insurance.getId() + "," + insurance.isAuthorization() + "," + null
            + ",'" + insurance.getCreatedDate() + "','" + insurance.getExplanation() + "','" + insurance.getModifiedDate() + "','"
            + insurance.getName() + "'," + insurance.getPremium() + ",'" + insurance.getType() + "'" + ");";
    if (super.create(query)) {
      if (insurance instanceof FireInsurance) {
        fireInsuranceDAO.add((FireInsurance) insurance);
      } else if (insurance instanceof CarInsurance) {
        carInsuranceDAO.add((CarInsurance) insurance);
      }else if (insurance instanceof SeaInsurance) {
        seaInsuranceDAO.add((SeaInsurance) insurance);
      }

      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean delete(int insuranceId) {
    String query = "DELETE FROM insurance WHERE id =  " + insuranceId + ";";
    if (super.delete(query)) {
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean update(Insurance insurance) {
    String query = "UPDATE insurance set authorization = "
            + insurance.isAuthorization() + ", authorizedDate = '" + insurance.getAuthorizedDate()
            + "', createdDate = '" + insurance.getCreatedDate() + "', explanation = '" + insurance.getExplanation()
            + "', modifiedDate = '" + insurance.getModifiedDate() + "', name = '"
            + insurance.getName() + "', premium = " + insurance.getPremium() + ", type = '" + insurance.getType()
            + "' where id = " + insurance.getId();
    if (super.update(query)) {
      if (insurance instanceof FireInsurance) {
        fireInsuranceDAO.update((FireInsurance) insurance);
      } else if (insurance instanceof CarInsurance) {
        carInsuranceDAO.update((CarInsurance) insurance);
      }else if (insurance instanceof SeaInsurance) {
        seaInsuranceDAO.update((SeaInsurance) insurance);
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Insurance get(int insuranceId) {
    for (Insurance insurance : getInsuranceList()) {
      if (insuranceId == insurance.getId()) {
        return insurance;
      }
    }
    return null;
  }

  @Override
  public int getSize() {
    return getInsuranceList().size();
  }

}
