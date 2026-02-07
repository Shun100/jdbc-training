package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

  /**
   * パスワード取得
   * @param String propertiesFile - プロパティファイル
   * @return String password
   * @throws IOException
   */
  public static String getPassword(String propertiesFile) throws IOException {
    Properties properties = new Properties();

    try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(propertiesFile)) {
      if (input == null) {
        throw new IOException(".propertiesファイルが存在しません");
      }

      properties.load(input);
      return properties.getProperty("db.password");
    }
  }
}
