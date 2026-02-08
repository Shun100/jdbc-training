import java.io.IOException;

import dao.Delete;
import dao.Insert;
import dao.Select;
import dao.Transaction;
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
    insert.executeWithPreparedStatement("Oracle Bronze", "Database");

    // select
    Select select = new Select();
    select.execute("Deep Learning", "AI");
    select.executeWithPreparedStatement("ゼロから作るDeep Learning", "book");

    // update
    Update update = new Update();
    update.execute("TCP/IPの全て", "network");
    update.executeWithPreparedStatement("Clean Architecture", "architecture");

    // delete
    Delete delete = new Delete();
    delete.execute("Deep Learning", "book");
    delete.executeWithPreparedStatement("TCP/IPの全て", "network");

    // SQLインジェクションの例
    delete.execute("Deep Learning' OR '1' = '1", "book' OR '1' = '1"); // 全て削除される

    // transaction
    Transaction transaction = new Transaction();
    transaction.execute();
  }
}
