package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete extends AbstractDao {
  public Delete() throws IOException {
    super();
  }

  /**
   * Delete
   * - 構文
   *  DELETE FROM <テーブル名> WHERE <条件>
   */
  public void execute(String productName, String category) {
    String sql = new StringBuilder()
      .append("DELETE FROM product ")
      .append(String.format("WHERE product_name = '%s' AND product_category = '%s'", productName, category))
      .toString();
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();) {
          int count = stmt.executeUpdate(sql);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void executeWithPreparedStatement(String productName, String category) {
    String sql = new StringBuilder()
      .append("DELETE FROM product ")
      .append("WHERE product_name = ? AND product_category = ?")
      .toString();
    
    try(Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);) {
          pstmt.setString(1, productName);
          pstmt.setString(2, category);
          int count = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
