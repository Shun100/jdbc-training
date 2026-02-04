import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://host.docker.internal:5432/sampledb?options=-c%20lc_messages=C";
    String user = "postgres";
    String password = "xxxxxxxx";

    try (Connection conn = DriverManager.getConnection(url, user, password);
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT now()")) {
            
            while (rs.next()) {
              System.out.println("DB time: " + rs.getTimestamp(1));
            }
    } catch (SQLException e) {
      System.err.println(e);
    }
  }
}
