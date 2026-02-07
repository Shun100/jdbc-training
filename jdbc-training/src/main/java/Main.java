import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws IOException {
    String url = "jdbc:postgresql://host.docker.internal:5432/postgres?options=-c%20lc_messages=C";
    String user = "postgres";
    String password = readPassword("password.properties");

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

  public static String readPassword(String propertiesFile) throws IOException {
    Properties properties = new Properties();

    try (InputStream input = Main.class.getClassLoader().getResourceAsStream(propertiesFile)) {
      if (input == null) {
        throw new IOException(".propertiesファイルが存在しません");
      }

      properties.load(input);
      return properties.getProperty("db.password");
    }
  }
}
