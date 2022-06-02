package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
  private String server = "localhost"; // MySQL 서버 주소
  private String database = "insurance_system"; // MySQL DATABASE 이름
  private String user_name = "root"; //  MySQL 서버 아이디
  private String password = "yeol!060913"; // MySQL 서버 비밀번호
  private Connection con;
  private Statement stmt;
  private ResultSet rs;

  public Connection getConnection(){
    // 1.드라이버 로딩
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
      e.printStackTrace();
    }

    // 2.연결
    try {
      con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&useSSL=false", user_name, password);
      stmt = con.createStatement();
      System.out.println("정상적으로 연결되었습니다.");

      stmt.executeUpdate("use insurance_system");
      stmt.close();

      this.con = con;

      return con;
    } catch(SQLException e) {
      System.err.println("con 오류:" + e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  public void releaseConnection(Connection con){
    try {
      if(con != null)
        con.close();
      System.out.println("DB 연결이 정상적으로 해제되었습니다.");
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
  }

  public boolean create(String query) {
    try {
      stmt = con.createStatement();
      stmt.executeUpdate(query);

      stmt.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return false;

  }

  public ResultSet retreive(String query) {
    try {
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);

      return rs;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean update(String query){
    try {
      stmt = con.createStatement();
      stmt.executeUpdate(query);

      stmt.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean delete(String query){
    try {
      stmt = con.createStatement();
      stmt.executeUpdate(query);

      stmt.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

}
