package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Select extends AbstractDao {
  public Select() throws IOException {
    super();
  }

  public void execute(String productName, String category) {
    String sql = new StringBuilder()
      .append("SELECT product_name, product_category FROM product ")
      .append(String.format("WHERE product_name = '%s' AND product_category = '%s'", productName, category))
      .toString();

    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        System.out.println(rs.getString("product_name") + " " + rs.getString("product_category"));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  public void executeWithPreparedStatement(String productName, String category) {
    String sql = new StringBuilder()
      .append("SELECT product_name, product_category FROM product ")
      .append("WHERE product_name = ? AND product_category = ?")
      .toString();
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, productName);
        pstmt.setString(2, category);

        try (ResultSet rs = pstmt.executeQuery()) {
          while (rs.next()) {
            System.out.println(rs.getString("product_name") + " " + rs.getString("product_category"));
          }
        }
      
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
