import java.io.IOException;

import dao.Insert;
import dao.Select;
import dao.Update;

public class Main {
  public static void main(String[] args) throws IOException {
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

    // insert
    Insert insert = new Insert();
    insert.execute("詳細LLM", "AI");

    // select
    Select select = new Select();
    select.execute("Deep Learning", "book");
    select.executeWithPreparedStatement("ゼロから作るDeep Learning", "book");

    // update
    Update update = new Update();
    update.execute("TCP/IPの全て", "network");
  }
}
