package bank.models;

import utility.formatting.CurrencyHelper;

import java.util.Objects;

public class Account {
    private final int AccountNumber;
    private String Name;
    private double Balance;

    /***
     * Create new Account
     * @param Name Must be not be null or empty
     * @param Balance Always positive values
     */
    public Account(int AccountNumber, String Name, double Balance)
    {
        validateName(Name);
        validateBalance(Balance);

        this.AccountNumber  = AccountNumber;
        this.Name = Name;
        this.Balance = Balance;

    }

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

    // Getters Methods
    public int getAccountNumber()
    {
        return AccountNumber;
    }

    public String getName()
    {
        return Name;
    }

    public double getBalance()
    {
        return Balance;
    }



    // Action Methods
    private boolean canDeposit(double amount)
    {
        if(amount <= 0 )
            throw new IllegalArgumentException("You can't make a deposit of negative or zero amount");
        return true;
    }

    /***
     *
     * @param amount Its mandatory be positive.
     * @throws IllegalAccessException if amount is less or equal to zero.
     *
     */
    public void deposit(double amount)
    {
        if(canDeposit(amount))
            this.Balance += amount;
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
            this.Balance -= amount;
    }


    //Setters Methods
    public void setName(String Name)
    {
        validateName(Name);
        this.Name = Name;
    }



    @Override
    public String toString()
    {
        return "Account Number : " + getAccountNumber() +
                "Client Name : " + getName() +
                "Balance : " + CurrencyHelper.getLocalCurrencyFormatter().format(getBalance());
    }
}
