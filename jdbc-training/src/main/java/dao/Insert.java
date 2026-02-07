package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Insert extends AbstractDao {
  public Insert() throws IOException {
    super();
  }

  /**
   * INSERT
   * - 構文
   *  INSERT INTO <テーブル名> (列1, 列2, 列3, ...) VALUES (値1, 値2, 値3, ...);
   */
  public void execute(String productName, String category) {
    String sql =  new StringBuilder()
      .append("INSERT INTO product ")
      .append("(product_name, product_category) ")
      .append(String.format("VALUES ('%s', '%s')", productName, category))
      .toString();
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();) {
      int count = stmt.executeUpdate(sql); // INSERTであってもexecuteUpdateメソッドを使う
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void executeWithPreparedStatement(String productName, String category) {}

}
