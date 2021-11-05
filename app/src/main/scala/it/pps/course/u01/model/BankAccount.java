package it.pps.course.u01.model;

/**
 * This interface defines the concept of a very basic bank account.
 */
public interface BankAccount {

    /**
     * Allows to know who is the holder of this bank account
     * @return the AccountHolder instance related to this bank account.
     */
    AccountHolder getHolder();

    /**
     * Returns the current balance of the bank account
     * @return the current balance
     */
    double getBalance();

    /**
     * Allows the deposit of an amount on the account, if the given usrID corresponds to the register holder ID
     * of the bank account. This ID acts like an "identification token" .
     *
     * @param usrID the id of the user that wants do the deposit
     * @param amount the amount of the deposit
     */
    void deposit(int usrID, double amount);

    /**
     * Allows the withdraw of an amount from the account, if the given usrID corresponds to the register holder ID
     * of the bank account. This ID acts like an "identification token" .
     *
     * @param usrID the id of the user that wants do the withdraw
     * @param amount the amount of the withdraw
     */
    void withdraw(int usrID, double amount);
}
