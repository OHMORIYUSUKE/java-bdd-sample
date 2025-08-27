# Java BDD サンプルプロジェクト

このプロジェクトは、JavaでBDD（Behavior Driven Development）を実践するためのサンプルです。Cucumber-JVMとJUnit 5を使用して、ビジネス要件を自然言語で記述し、それを自動テストとして実行できるようにしています。

## プロジェクト構成

```
src/
├── main/java/com/example/bdd/
│   └── BankAccount.java         # 銀行口座クラス（預金、引き出し、口座開設）
└── test/
    ├── java/com/example/bdd/
    │   ├── BankAccountSteps.java    # 銀行口座のステップ定義
    │   └── CucumberTestRunner.java  # Cucumberテストランナー
    └── resources/features/
        ├── account/
        │   ├── management/           # 口座管理
        │   │   └── opening.feature  # 口座開設
        │   └── transaction/         # 口座取引
        │       ├── deposit.feature  # 預金
        │       └── withdrawal.feature # 引き出し
```

## 技術スタック

- **Java 11**
- **Gradle 8.5+**
- **Cucumber-JVM 7.15.0**
- **JUnit 5.10.0**
- **AssertJ 3.24.2**（アサーション用）

## セットアップ

### 前提条件

- Java 11以上がインストールされていること
- Gradle 8.5以上がインストールされていること（またはGradleラッパーを使用）

### インストール

1. リポジトリをクローンまたはダウンロード
2. プロジェクトディレクトリに移動
3. 初回セットアップ（Gradle Wrapperのダウンロード）：

```bash
# Gradle Wrapperのダウンロード（初回のみ必要）
curl -o gradle/wrapper/gradle-wrapper.jar https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar

# 依存関係のダウンロードとビルド
./gradlew clean build

# または、テストのみ実行
./gradlew test
```

**注意**: `gradle-wrapper.jar`は手動でダウンロードする必要があります。`gradle-wrapper.properties`の内容に基づいて適切なGradleバージョンが自動的にダウンロードされます。

## 使用方法

### テストの実行

#### 全テストの実行

```bash
./gradlew test
```

#### 特定のフィーチャーの実行

```bash
# 口座開設のテストのみ実行
./gradlew test --args="--cucumber.features=src/test/resources/features/account/management/opening.feature"

# 預金のテストのみ実行
./gradlew test --args="--cucumber.features=src/test/resources/features/account/transaction/deposit.feature"

# 引き出しのテストのみ実行
./gradlew test --args="--cucumber.features=src/test/resources/features/account/transaction/withdrawal.feature"
```

#### Cucumberテストランナーを使用した実行

```bash
./gradlew cucumberTest
```

### フィーチャーファイルの記述

フィーチャーファイルは、ビジネス要件を自然言語で記述します。例：

```gherkin
Feature: Bank Account
  In order to manage my money safely
  As a bank customer
  I want to perform various banking operations

  Scenario: Depositing money into account
    Given a bank account exists with account number "12345" and holder "John Doe" and initial balance 1000.0
    When the customer deposits 500.0
    Then the account balance should be 1500.0
```

### ステップ定義の作成

フィーチャーファイルの各ステップに対応するJavaコードを作成します：

```java
@When("the customer deposits {double}")
public void theCustomerDeposits(double amount) {
    currentAccount.deposit(amount);
}

@Then("the account balance should be {double}")
public void theAccountBalanceShouldBe(double expectedBalance) {
    Assertions.assertThat(currentAccount.getBalance()).isEqualTo(expectedBalance);
}
```

## BDDの利点

1. **ビジネス要件の明確化**: フィーチャーファイルにより、技術者以外の関係者も理解しやすい
2. **テスト駆動開発**: 要件からテストが自動生成され、品質向上に貢献
3. **ドキュメント化**: フィーチャーファイルが自動的に最新の仕様書となる
4. **チーム協力**: 開発者、テスター、ビジネス関係者の協力が促進される

## サンプルシナリオ

### 口座管理
- 口座開設（普通預金口座）
- 口座開設のバリデーション（無効な初期残高など）

### 口座取引
- 預金・引き出し
- 取引のバリデーション（残高不足、負の金額、ゼロ円など）
- 取引履歴の記録

## カスタマイズ

新しいフィーチャーを追加する場合：

1. `src/test/resources/features/` の適切なサブディレクトリに新しい `.feature` ファイルを作成
   - 口座管理関連: `account/management/`
   - 取引関連: `account/transaction/`
2. `src/main/java/com/example/bdd/` にビジネスロジッククラスを作成
3. `src/test/java/com/example/bdd/` にステップ定義クラスを作成
4. 必要に応じて `build.gradle` に依存関係を追加

## トラブルシューティング

### よくある問題

1. **Gradle Wrapperが動作しない**: 初回実行時は `./gradlew --version` でGradle Wrapperをダウンロード
2. **Cucumberステップが見つからない**: ステップ定義クラスが正しいパッケージに配置されているか確認
3. **フィーチャーファイルが読み込まれない**: `src/test/resources/features/` ディレクトリに配置されているか確認
4. **依存関係の問題**: `./gradlew clean build` で依存関係を再ダウンロード

### ログの確認

詳細なログを確認するには：

```bash
./gradlew test --info
```

## ライセンス

このプロジェクトは教育目的で作成されており、自由に使用・改変できます。
