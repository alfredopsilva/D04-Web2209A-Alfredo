package bank.models;

public class BalanceChangedEvent {
    private final int accountNumber;
    private double previousBalance;
    private double balance;
    public BalanceChangedEvent(int accountNumber,double previousBalance, double balance) {
        this.previousBalance = previousBalance;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    //Getters
    public int getAccountNumber()
    {
        return accountNumber;
    }
    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getBalance() {
        return balance;
    }
}
