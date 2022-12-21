package bank.models;

import utility.formatting.CurrencyHelper;

import java.util.Objects;

public class Account {
    private final int AccountNumber;
    private String name;
    private double balance;

    /***
     * Create new Account
     * @param name Must be not be null or empty
     * @param Balance Always positive values
     * @param AccountNumber Always positive values
     */
    public Account(int AccountNumber, String name, double Balance)
    {
        validateName(name);
        validateBalance(Balance);

        this.AccountNumber  = AccountNumber;
        this.name = name;
        this.balance = Balance;

    }

    // Getters Methods

    public int getAccountNumber()
    {
        return AccountNumber;
    }
    public String getName()
    {
        return name;
    }

    public double getBalance()
    {
        return balance;
    }
    // Action Methods
    private boolean canDeposit(double amount)
    {
        if(amount <= 0 )
            throw new IllegalArgumentException("You can't make a deposit of negative or zero amount");
        return true;
    }

    /***
     * @param amount Its mandatory be positive.
     * @throws IllegalAccessException if amount is less or equal to zero.
     */
    public void deposit(double amount)
    {
        if(canDeposit(amount))
            setBalance(balance + amount);
    }
    private boolean canWithdraw(double amount)
    {
        if(amount > getBalance())
            throw new IllegalArgumentException("You can't make a withdrawal bigger than your balance. Be Humble!");
        if(amount <= 0)
            throw new IllegalArgumentException("How can you withdraw zero or less than zero?");
        return true;
    }
    public void withdrawal(double amount)
    {
        if(canWithdraw(amount))
            setBalance(balance - amount);
    }

    //Setters Methods

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setName(String name)
    {
        validateName(name);
        this.name = name;
    }
    @Override
    public String toString()
    {
        return "Account Number : " + getAccountNumber() +
                "Client Name : " + getName() +
                "Balance : " + CurrencyHelper.getLocalCurrencyFormatter().format(getBalance());
    }
    //Validation Methods

    private static void validateName(String name)
    {
        Objects.requireNonNull(name);
        if(name.isBlank())
            throw new IllegalArgumentException("Name is Empty. You need to insert a value.");
    }
    private static void validateBalance(double Balance)
    {
        if(Balance < 0)
            throw new IllegalArgumentException("Balance can't be negative. Insert a value");

    }
}
