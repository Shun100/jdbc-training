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

    /**
     * productテーブルの構造
     * - product_id INTEGER ALWAYS GENERATED AS IDENTITY
     * - product_name VARCHAR(32) NOT NULL
     * - product_category VARCHAR(32) NOT NULL
     * - create_at timestamp DEFAULT CURRENT_TIMESTAMP
     * - updated_at timestamp DEFAULT CURRENT_TIMESTAMP
     */

    // SELECT
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
            
      while (rs.next()) {
        System.out.println(rs.getString("product_name"));
      }
    } catch (SQLException e) {
      System.err.println(e);
    }

    /**
     * INSERT
     * - 構文
     *  INSERT INTO <テーブル名> (列1, 列2, 列3, ...) VALUES (値1, 値2, 値3, ...);
     */
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();) {
      StringBuilder sqlBuilder = new StringBuilder();
      sqlBuilder.append("INSERT INTO product ");
      sqlBuilder.append("(product_name, product_category) ");
      sqlBuilder.append("VALUES ('詳細LLM', 'AI')");

      int count = stmt.executeUpdate(sqlBuilder.toString()); // INSERTであってもexecuteUpdateメソッドを使う
    } catch (SQLException e) {
      System.err.println(e);
    }

    /**
     * UPDATE
     * - 構文
     *  UPDATE <テーブル名> SET <列名> = <式>, <列名> = <式> ... WHERE <列名> = <式>;
     */
    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();) {
      String sql = new StringBuilder()
        .append("UPDATE ")
        .append("product ")
        .append("SET ")
        .append("product_name = '図解 TCP/IP', ")
        .append("updated_at = CURRENT_TIMESTAMP(0) ")
        .append("WHERE product_id = 1")
        .toString();
      int count = stmt.executeUpdate(sql);
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
