package bank.models;

public class NameChangeEvent
{
    private final int accountNumber;
    private final String previousName;
    private final String name;

    public NameChangeEvent(int accountNumber, String previousName, String name)
    {
        this.accountNumber = accountNumber;
        this.previousName = previousName;
        this.name = name;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getPreviousName()
    {
        return previousName;
    }

    public String getName()
    {
        return name;
    }
}
