# jdbc-training
JDBC学習用

## 実行方法
- イメージビルド
  - `docker build --no-cache -t jdbc-training .`
- コンテナ起動 -> Java実行
  - `docker run -it --rm jdbc-training`
- ビルド(コンテナ内で実行)
  - ビルドと実行を一括
    - `gradle run`
  - ビルドと実行を別々
    - `gradle build`
    - `java -jar build/libs/jdbc-training.jar`

## ドライバクラス名
- H2: `org.h2.Driver`
- MySQL: `com.mysql.cj.jdbc.Driver`