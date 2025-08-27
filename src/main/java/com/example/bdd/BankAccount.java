package com.example.bdd;

/**
 * 銀行口座クラス
 * BDDテストの対象となる基本的な銀行口座の操作を提供します
 */
public class BankAccount {
    
    private double balance;
    private String accountNumber;
    private String accountHolder;
    
    /**
     * 銀行口座を初期化します
     * @param accountNumber 口座番号
     * @param accountHolder 口座名義人
     * @param initialBalance 初期残高
     * @throws IllegalArgumentException 初期残高が負の値の場合
     */
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("初期残高は正の値である必要があります");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    /**
     * 口座番号を取得します
     * @return 口座番号
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * 口座名義人を取得します
     * @return 口座名義人
     */
    public String getAccountHolder() {
        return accountHolder;
    }
    
    /**
     * 現在の残高を取得します
     * @return 現在の残高
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * 口座に預金します
     * @param amount 預金額
     * @throws IllegalArgumentException 預金額が負の値またはゼロの場合
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("預金額は正の値である必要があります");
        }
        balance += amount;
    }
    
    /**
     * 口座から引き出します
     * @param amount 引き出し額
     * @throws IllegalArgumentException 引き出し額が負の値またはゼロの場合
     * @throws IllegalStateException 残高が不足している場合
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("引き出し額は正の値である必要があります");
        }
        if (amount > balance) {
            throw new IllegalStateException("残高が不足しています");
        }
        balance -= amount;
    }
    
}
