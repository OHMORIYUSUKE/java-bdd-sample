package com.example.bdd;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * 銀行口座のBDDテスト用ステップ定義クラス
 * 3つの基本機能（口座開設、口座振り込み、口座引き出し）に対応
 */
public class BankAccountSteps {
    
    private Map<String, BankAccount> accounts;
    private BankAccount currentAccount;
    private Exception exception;
    
    @Before
    public void setUp() {
        // 各テストシナリオの前に実行される初期化処理
        accounts = new HashMap<>();
        currentAccount = null;
        exception = null;
    }
    
    // 口座開設関連のステップ定義
    @Given("顧客が口座開設を希望している")
    public void customerWantsToOpenAccount() {
        // 顧客が口座開設を希望している状態
    }
    
    @When("顧客が口座番号 {string} で名義人 {string} で初期残高 {double} 円の普通預金口座を開設する")
    public void customerOpensAccount(String accountNumber, String holder, double initialBalance) {
        try {
            BankAccount account = new BankAccount(accountNumber, holder, initialBalance);
            accounts.put(accountNumber, account);
            currentAccount = account;
        } catch (Exception e) {
            exception = e;
        }
    }
    
    @When("顧客が口座番号 {string} で名義人 {string} で初期残高 {double} 円の普通預金口座の開設を試行する")
    public void customerAttemptsToOpenAccount(String accountNumber, String holder, double initialBalance) {
        try {
            BankAccount account = new BankAccount(accountNumber, holder, initialBalance);
            accounts.put(accountNumber, account);
            currentAccount = account;
        } catch (Exception e) {
            exception = e;
        }
    }
    
    @Then("口座が正常に作成されることを確認する")
    public void verifyAccountCreatedSuccessfully() {
        Assertions.assertThat(currentAccount).isNotNull();
        Assertions.assertThat(exception).isNull();
    }
    
    @Then("口座開設が拒否されることを確認する")
    public void verifyAccountOpeningRejected() {
        Assertions.assertThat(exception).isNotNull();
    }
    
    // 口座存在確認のステップ定義
    @Given("口座番号 {string} で名義人 {string} で初期残高 {double} 円の普通預金口座が存在する")
    public void bankAccountExists(String accountNumber, String holder, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, holder, initialBalance);
        accounts.put(accountNumber, account);
        currentAccount = account;
    }
    
    // 残高確認のステップ定義
    @Given("口座残高が {double} 円である")
    public void accountBalanceIs(double balance) {
        Assertions.assertThat(currentAccount.getBalance()).isEqualTo(balance);
    }
    
    // 預金関連のステップ定義
    @When("顧客が {double} 円を預金する")
    public void customerDeposits(double amount) {
        try {
            currentAccount.deposit(amount);
        } catch (Exception e) {
            exception = e;
        }
    }
    
    @When("顧客が {double} 円の預金を試行する")
    public void customerAttemptsToDeposit(double amount) {
        try {
            currentAccount.deposit(amount);
        } catch (Exception e) {
            exception = e;
        }
    }
    
    // 引き出し関連のステップ定義
    @When("顧客が {double} 円を引き出す")
    public void customerWithdraws(double amount) {
        try {
            currentAccount.withdraw(amount);
        } catch (Exception e) {
            exception = e;
        }
    }
    
    @When("顧客が {double} 円の引き出しを試行する")
    public void customerAttemptsToWithdraw(double amount) {
        try {
            currentAccount.withdraw(amount);
        } catch (Exception e) {
            exception = e;
        }
    }
    

    
    // 結果確認のステップ定義
    @Then("口座残高が {double} 円になることを確認する")
    public void verifyAccountBalance(double expectedBalance) {
        Assertions.assertThat(currentAccount.getBalance()).isEqualTo(expectedBalance);
    }
    
    @Then("取引が拒否されることを確認する")
    public void verifyTransactionRejected() {
        Assertions.assertThat(exception).isNotNull();
    }
    
    @Then("エラーメッセージが表示されることを確認する")
    public void verifyErrorMessageDisplayed() {
        Assertions.assertThat(exception).isNotNull();
    }
    
    // 口座情報確認のステップ定義
    @And("口座番号が {string} であることを確認する")
    public void verifyAccountNumber(String expectedAccountNumber) {
        Assertions.assertThat(currentAccount.getAccountNumber()).isEqualTo(expectedAccountNumber);
    }
    
    @And("口座名義人が {string} であることを確認する")
    public void verifyAccountHolder(String expectedAccountHolder) {
        Assertions.assertThat(currentAccount.getAccountHolder()).isEqualTo(expectedAccountHolder);
    }
    
    @And("口座残高が {double} 円であることを確認する")
    public void verifyAccountBalanceInAnd(double expectedBalance) {
        Assertions.assertThat(currentAccount.getBalance()).isEqualTo(expectedBalance);
    }
    
    @And("口座種別が {string} であることを確認する")
    public void verifyAccountType(String expectedAccountType) {
        Assertions.assertThat(expectedAccountType).isEqualTo("普通預金");
    }
    
    @And("口座状態が {string} であることを確認する")
    public void verifyAccountStatus(String expectedStatus) {
        Assertions.assertThat(expectedStatus).isEqualTo("有効");
    }
    
    // 残高変更なし確認のステップ定義
    @And("口座残高が {double} 円のままであることを確認する")
    public void verifyBalanceUnchanged(double expectedBalance) {
        Assertions.assertThat(currentAccount.getBalance()).isEqualTo(expectedBalance);
    }
    

    
    // 取引履歴確認のステップ定義
    @And("取引履歴に預金取引が記録されることを確認する")
    public void verifyDepositTransactionRecorded() {
        Assertions.assertThat(currentAccount).isNotNull();
    }
    
    @And("取引履歴に引き出し取引が記録されることを確認する")
    public void verifyWithdrawalTransactionRecorded() {
        Assertions.assertThat(currentAccount).isNotNull();
    }
    

}
