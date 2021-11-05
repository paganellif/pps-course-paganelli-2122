package it.pps.course.u01.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount implements BankAccountWithAtm{

    private double fees;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance, double fees) {
        super(holder, balance);
        this.fees = fees;
    }

    @Override
    public double getFees() {
        return this.fees;
    }

    @Override
    public void depositWithAtm(final int usrID, final double amount) {
        if (this.isAmountPositive(amount)){
            double realAmount = amount - this.fees;
            super.deposit(usrID, realAmount);
        }
    }

    @Override
    public void withdrawWithAtm(final int usrID, final double amount) {
        if (this.isAmountPositive(amount)){
            double realAmount = amount + this.fees;
            super.withdraw(usrID, realAmount);
        }
    }

    private boolean isAmountPositive(final double amount) {
        return amount > 0;
    }
}
