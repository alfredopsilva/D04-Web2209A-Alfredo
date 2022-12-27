package bank.models;

import utility.formatting.CurrencyHelper;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
    private final int AccountNumber;
    private String name;
    private double balance;
    private ArrayList<IAccountListener> listeners;

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

        this.AccountNumber  = AccountNumber; // Its final
        this.name = name; // Event
        this.balance = Balance; // Event
        listeners = new ArrayList<>();

        Thread thread = new Thread(this::collectFees);
        thread.setDaemon(true);
        thread.start();

    }

    private void collectFees()
    {
        while(true)
        {
            sleepThread(2);
            double fee = calculateFee();
            if(fee > 0){
                withdrawFee(fee);
            }
        }
    }

    private static void sleepThread(double seconds) {
        try {
            long milliseconds = (long) (seconds * 1000);
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private double calculateFee()
    {
        if(balance >= 5000)
            return 0;
        if(balance >= 1000)
            return 3;
        if(balance >= 10)
            return 10;

        return balance;
    }

    private void withdrawFee(double fee)
    {
        withdrawal(fee);
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
     */
    public void deposit(double amount)
    {
        if(canDeposit(amount))
            setBalance(balance + amount); // Event Change
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
            setBalance(balance - amount); // Event Change
    }

    //Setters Methods

    private void setBalance(double balance)
    {
        validateBalance(balance);

        if (this.balance != balance)
        {
            double previousBalance = this.balance;

            this.balance = balance;

            for (IAccountListener listener : listeners)
                listener.changeBalance(new BalanceChangedEvent(AccountNumber, previousBalance, balance));
        }
    }
    public void setName(String name) throws AccountException
    {
        validateName(name);

        if (!this.name.equals(name))
        {
            String previousName = this.name;

            this.name = name;

            for (IAccountListener listener : listeners)
                listener.changeName(new NameChangeEvent(AccountNumber, previousName, name));
        }
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
    private static void validateBalance(double Balance) {
        if(Balance < 0)
            throw new IllegalArgumentException("Balance can't be negative. Insert a value");

    }

    public void addListener(IAccountListener listener)
    {
        listeners.add(listener);
    }

    public void removeListener(IAccountListener listener)
    {
        listeners.remove(listener);
    }
}
