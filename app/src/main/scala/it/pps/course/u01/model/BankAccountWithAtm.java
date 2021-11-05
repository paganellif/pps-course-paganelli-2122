package it.pps.course.u01.model;

public interface BankAccountWithAtm extends BankAccount{
    /**
     *
     * @return the current fees
     */
    double getFees();

    void depositWithAtm(int usrID, double amount);

    void withdrawWithAtm(int usrID, double amount);

}
