package bank.controllers;

import bank.models.Account;
import bank.views.AccountView;
import utility.formatting.CurrencyHelper;
import java.text.NumberFormat;

import java.awt.event.ActionEvent;
import java.util.Objects;

public class AccountController {
    private final Account account;
    private final AccountView view;
    private final NumberFormat currencyFormatter;

    public AccountController(Account account, AccountView view)
    {
        this.account = Objects.requireNonNull(account);
        this.view = Objects.requireNonNull(view);
        currencyFormatter = CurrencyHelper.getLocalCurrencyFormatter();

        view.addDepositListener(this::onDepositClicked);
        view.addWithdrawalListener(this::onWithdrawalClicked);
    }


    private void onWithdrawalClicked(ActionEvent event) {
        try
        {
            System.out.println("Withdrawal Button { Clicked }");
            double amount = Double.parseDouble(view.getAmountField());

            account.withdrawal(amount);
            // VIEW: update view
            view.updateBalance(account.getBalance());
            view.displayMessage("Withdrawal completed successfully " + currencyFormatter.format(amount));
        }
        catch (NumberFormatException e)
        {
            view.displayMessage("Invalid amount");
            view.resetAmount();
        }
        catch (IllegalArgumentException e)
        {
            view.displayMessage(e.getMessage());
            view.resetAmount();
        }
    }

    private void onDepositClicked(ActionEvent event)
    {
        try
        {
            System.out.println("Deposit Button { Clicked }");
            double amount = Double.parseDouble(view.getAmountField());

            account.deposit(amount);
            view.updateBalance(account.getBalance());
            view.displayMessage("Deposit completed successfully " + currencyFormatter.format(amount));

        }
        catch (NumberFormatException e)
        {
            view.displayMessage("Invalid amount");
            view.resetAmount();
        }
        catch (IllegalArgumentException e)
        {
            view.displayMessage(e.getMessage());
            view.resetAmount();
        }
    }








}
