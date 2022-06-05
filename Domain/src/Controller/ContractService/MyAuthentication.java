package Controller.ContractService;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthentication extends Authenticator {

    private PasswordAuthentication account;
    private int count;

    public MyAuthentication() {
      String id = "hakgooyeol";
      String pw = "5SS8XH6C5PVP";
      account = new PasswordAuthentication(id, pw);
      count = 0;
    }

    public int getCount() {
        return count;
    }

    public PasswordAuthentication getPasswordAuthentication() {
      return account;
    }
  }

