package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
  private final String URL = "jdbc:mysql://localhost:3306/pc_shop_db";
  private final String USER = "shop_user";
  private final String PASSWORD = "pass";

  public void connect() throws SQLException {
    // ConnectionクラスはAutoClosableインタフェースを実装しているので、try-with-resource文に対応している
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
      System.out.println("接続しました");
    } catch (SQLException e) {
      throw e;
    }
  }
}
