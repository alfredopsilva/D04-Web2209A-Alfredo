package bank.views;

import bank.models.Account;
import bank.models.BalanceChangedEvent;
import bank.models.IAccountListener;
import bank.models.NameChangeEvent;
import utility.formatting.CurrencyHelper;
import utility.swing.components.InputField;
import utility.swing.layout.LayoutHelper;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel implements IAccountListener
{
    private final AccountDataView  accountDataView;
    private final JLabel messageLabel;
    private final InputField amountField;
    private final Account account;
    private final JButton depositButton;
    private final JButton withdrawButton;


    public AccountView(Account account)
    {
        this.account = account;
        accountDataView = new AccountDataView(account);

        messageLabel = new JLabel("Enter an amount");
        messageLabel.setAlignmentX(0.5f);

        amountField = new InputField("Amount");


        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        add(accountDataView);
        add(LayoutHelper.createRigidArea(20));
        add(createMessagePanel());
        add(LayoutHelper.createRigidArea(20));
        add(amountField);
        add(LayoutHelper.createRigidArea(20));
        add(createButtonsPanel());

    }
    private JPanel createMessagePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(messageLabel);
        return panel;
    }

    private JPanel createButtonsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(depositButton);
        panel.add(LayoutHelper.createSmallRigidArea());
        panel.add(withdrawButton);
        return panel;
    }

    public String getAmountField()
    {
        return amountField.getText();
    }

    public void updateName(String name)
    {
        accountDataView.updateName(name);
    }


    // Action-Listener
    public void addWithdrawalListener(ActionListener listener)
    {
        withdrawButton.addActionListener(listener);
    }

    public void addDepositListener(ActionListener listener)
    {
        depositButton.addActionListener(listener);
    }

    public void displayMessage(String message)
    {
        messageLabel.setText(message);
    }

    public void resetAmount()
    {
        amountField.clearText();
    }

    @Override
    public void changeName(NameChangeEvent event) {
        SwingUtilities.invokeLater(() -> accountDataView.changeName(event));
    }
    @Override
    public void changeBalance(BalanceChangedEvent event) {
        SwingUtilities.invokeLater(() -> accountDataView.changeBalance(event));
    }
}
