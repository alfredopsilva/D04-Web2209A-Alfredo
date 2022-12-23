package bank.models;

public class BalanceChangedEvent {
    private double previousBalance;
    private double balance;
    public BalanceChangedEvent(double previousBalance, double balance) {
        this.previousBalance = previousBalance;
        this.balance = balance;
    }

    //Getters
    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getBalance() {
        return balance;
    }
}
