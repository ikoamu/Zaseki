# Zaseki
座席検索アプリ

## 開発環境
* IDE
   * Eclipse Oxygen (4.7.3)
* フレームワーク
   *  SpringBoot (2.0.3)
* DB
   * PostgreSQL (9.5.13)
   
## 導入方法
1. gitでmasterブランチをclone
2. Eclipseで、ファイル→インポート→Gradle/Gradleプロジェクト、を選択してインポート
3. プロジェクトを右クリックし、Gradle→Gradleプロジェクトのリフレッシュ、を選択

### PostgreSQLの導入方法
1. `sudo apt-get update`でパッケージリストを更新
2. `sudo apt-get install postgresql`でインストール
3. `su - postgres`でユーザを切り替える
4. `psql`でログイン
5. `CREATE DATABASE zaseki;`でデータベースzasekiを作成
