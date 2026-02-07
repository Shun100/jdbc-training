package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Update extends AbstractDao {
  public Update() throws IOException {
    super();
  }

  /**
   * UPDATE
   * - 構文
   *  UPDATE <テーブル名> SET <列名> = <式>, <列名> = <式> ... WHERE <列名> = <式>;
   */
  public void execute(String productName, String category) {
    String sql = new StringBuilder()
      .append("UPDATE ")
      .append("product ")
      .append(String.format("SET product_name = '%s', product_category = '%s', ", productName, category))
      .append("updated_at = CURRENT_TIMESTAMP(0) ")
      .append("WHERE product_id = 1")
      .toString();

    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();) {
      int count = stmt.executeUpdate(sql);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void executeWithPreparedStatement(String productName, String category) {}
}
