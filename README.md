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
3. Gradleプロジェクトが選択できない場合、Buildshipをインストール  
Eclipseマーケットプレイスで`Buildship`を検索、インストール
4. プロジェクトを右クリックし、Gradle→Gradleプロジェクトのリフレッシュ、を選択

### PostgreSQLの導入方法
1. `sudo apt-get update`でパッケージリストを更新
2. `sudo apt-get install postgresql`でインストール
3. `su - postgres`でユーザを切り替える
4. `psql`でログイン
5. `CREATE DATABASE zaseki;`でデータベースzasekiを作成
6. src/main/resourcesの中にあるapplication.propertiesに設定を記入  
`spring.datasource.username`にPostgreSQLのユーザ名  
`spring.datasource.password`にPostgreSQLのパスワード

### Lombokの導入方法
* Lombokのjarファイルを[ダウンロード](https://projectlombok.org/download)、実行
* Eclipseを選択しインストール

### 操作方法
#### データ取得
* 全社員
  * GET `/member`

* よみがなを指定
  * GET `/member?yomigana={yomigana}`

* 部署を指定
  * GET `/member?div={div}`

* よみがなと部署を指定
  * GET `/member?yomigana={yomigana}&div={div}`

#### データ追加
* POST `/member`
  * リクエストボディ:
  ```
  {
    "name": "name　name"
  , "yomigana": "name"
  , "division": "division"
  , "floor": "floor"
  , "extensionNumber": "0000"
  }
  ```

#### データ更新
* PUT `/member?id={id}`
  * リクエストボディ:
  ```
  {
    "name": "name　name"
  , "yomigana": "name"
  , "division": "division"
  , "floor": "floor"
  , "extensionNumber": "0000"
  }
  ```

#### データ削除
* DELETE `/member?id={id}`

