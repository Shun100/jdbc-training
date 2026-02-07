package dao;

import java.io.IOException;

public abstract class AbstractDao {
  final String url;
  final String user;
  final String password;

  public AbstractDao() throws IOException {
    this.url = util.Const.URL;
    this.user = util.Const.USER;
    this.password = util.ConfigLoader.getPassword(util.Const.POPERTIES);
  }

  abstract public void execute(String productName, String category);
  abstract public void executeWithPreparedStatement(String productName, String category);
}
