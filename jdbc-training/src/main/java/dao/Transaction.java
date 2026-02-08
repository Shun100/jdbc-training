package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.ConfigLoader;
import util.Const;

public class Transaction {
  private final String url;
  private final String user;
  private final String password;

  public Transaction() throws IOException {
    this.url = Const.URL;
    this.user = Const.USER;
    this.password = ConfigLoader.getPassword(Const.PROPERTIES);
  }

  public void execute() {
    String sql = new StringBuilder()
      .append("INSERT INTO product (product_name, product_category) ")
      .append("VALUES ")
      .append("(?, ?), ")
      .append("(?, ?)")
      .toString();

    try (Connection conn = DriverManager.getConnection(url, user, password)) {
      conn.setAutoCommit(false); // BEGIN TRANSACTIONに相当

      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, "Design Pattern");
          pstmt.setString(2, "Programming");
          pstmt.setString(3, "TypeScript");
          pstmt.setString(4, "Programming");
          int count = pstmt.executeUpdate();
          conn.commit();  // commit
      } catch (SQLException e) {
        System.err.println(e);
        conn.rollback();  // rollback
      }
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
