package bank.controllers;

import bank.models.Account;
import bank.views.AccountView;

import java.util.Objects;

public class AccountController {
    private final Account account;
    private final AccountView view;

    AccountController(Account account, AccountView view)
    {
        this.account = Objects.requireNonNull(account);
        this.view = Objects.requireNonNull(view);
    }

}
