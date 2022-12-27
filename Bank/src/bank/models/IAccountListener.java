package bank.models;

public interface IAccountListener {
    void changeName(NameChangeEvent event);

    void changeBalance(BalanceChangedEvent event);
}


