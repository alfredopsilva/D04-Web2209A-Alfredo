package bank.views;

import bank.models.Account;
import utility.swing.components.InputField;
import utility.swing.components.LabelField;
import utility.swing.layout.LayoutHelper;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JPanel
{
    private final JLabel labelAccountNumber;
    private final JLabel valueAccountNumber;
    private final JLabel labelClientName;
    private final JLabel valueClientName;
    private final JLabel labelBalance;
    private final JLabel valueBalance;
    private final InputField amountField;
    private final Account account;
    private final JButton deposit;
    private final JButton withdraw;


    public AccountView(Account account)
    {
        this.account = account;
        labelAccountNumber = new JLabel("Account Number: ");
        valueAccountNumber = new JLabel(Integer.toString(account.getAccountNumber()));
        labelClientName = new JLabel("Client Name: ");
        valueClientName = new JLabel(account.getName());
        labelBalance = new  JLabel("Balance: ");
        valueBalance = new JLabel(Double.toString(account.getBalance()));

        JLabel label = new JLabel("Enter an amount");
        label.setAlignmentX(0.5f);
        amountField = new InputField("Amount");

        setBorder(LayoutHelper.createEmptyBorder());
        deposit = new JButton("Deposit");
        withdraw = new JButton("Withdraw");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        JPanel buttonSection = new JPanel();
        buttonSection.add(deposit);
        buttonSection.add(withdraw);
        buttonSection.setLayout(new FlowLayout());

        add(labelAccountNumber);
        add(LayoutHelper.createRigidArea(20));
        add(valueAccountNumber);
        add(LayoutHelper.createRigidArea(20));
        add(labelClientName);
        add(LayoutHelper.createRigidArea(20));
        add(valueClientName);
        add(LayoutHelper.createRigidArea(20));
        add(labelBalance);
        add(LayoutHelper.createRigidArea(20));
        add(valueBalance);
        add(LayoutHelper.createRigidArea(20));
        add(label);
        add(LayoutHelper.createRigidArea(20));
        add(amountField);
        add(LayoutHelper.createRigidArea(20));
        add(buttonSection);

        setLayout(new GridLayout(3, 2, LayoutHelper.DefaultSize, LayoutHelper.DefaultSize));
        // Why I have to create these methods inside the constructor?
        deposit();
        withdrawal();
    }

    private void withdrawal() {
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Withdrawal Button { Clicked }");
                // CONTROLLER: Get User Input
                double amount = Double.parseDouble(amountField.getText());
                // MODEL : Handle request by changing the Balance
                account.withdrawal(amount);
                // VIEW: update view
                valueBalance.setText(Double.toString(account.getBalance()));
            }
        });
    }

    private void deposit() {
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Deposit Button { Clicked }");
                // CONTROLLER: Get User Input
                double amount = Double.parseDouble(amountField.getText());
                // MODEL : Handle request by changing the Balance
                account.deposit(amount);
                // VIEW: update view
                valueBalance.setText(Double.toString(account.getBalance()));
            }
        });
    }

    public static void configureLookAndFeel() // Can change some settings from the interface.
    {
        useSystemLookAndFeel();

        Color backgroundColor = Color.white;
        ColorUIResource backgroundColorResource = new ColorUIResource(backgroundColor);
        UIManager.put("Panel.background", backgroundColorResource);
        UIManager.put("OptionPane.background", backgroundColorResource);
    }

    private static void useSystemLookAndFeel() // It's responsible to change de interface.
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
               | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
