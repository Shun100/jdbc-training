import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://host.docker.internal:5432/postgres?options=-c%20lc_messages=C";
    String user = "postgres";
    String password = "xxxxxxxx";

    /**
     * 主なResultSetのデータ取得メソッド
     * - getString("カラム名"): String
     * - getInt("カラム名"): int
     * - getDouble("カラム名"): double
     * - getDate("カラム名"): java.sql.Date
     */

    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
            
      while (rs.next()) {
        System.out.println(rs.getString("product_name"));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
