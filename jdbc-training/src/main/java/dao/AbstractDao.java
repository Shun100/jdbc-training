package dao;

import java.io.IOException;

import util.Const;
import util.ConfigLoader;

public abstract class AbstractDao {
  final String url;
  final String user;
  final String password;

  public AbstractDao() throws IOException {
    this.url = Const.URL;
    this.user = Const.USER;
    this.password = ConfigLoader.getPassword(Const.PROPERTIES);
  }

  abstract public void execute(String productName, String category);
  abstract public void executeWithPreparedStatement(String productName, String category);
}
