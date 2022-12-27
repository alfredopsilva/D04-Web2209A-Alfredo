package bank.views;

import bank.models.Account;
import bank.models.BalanceChangedEvent;
import bank.models.IAccountListener;
import bank.models.NameChangeEvent;
import utility.formatting.CurrencyHelper;
import utility.swing.layout.LayoutHelper;

import javax.swing.*;
import java.awt.*;

public class AccountDataView extends JPanel implements IAccountListener {

        private final JLabel nameLabel;
        private final JLabel accountNumberLabel;
        private final JLabel balanceLabel;

        public AccountDataView(Account account)
        {
            nameLabel = createValueLabel(account.getName());
            accountNumberLabel = createValueLabel(Integer.toString(account.getAccountNumber()));
            balanceLabel = createValueLabel(Double.toString(account.getBalance()));

            setLayout(new GridLayout(3, 2, LayoutHelper.DefaultSize, LayoutHelper.DefaultSize));

            add(createTitleLabel("Name"));
            add(nameLabel);
            add(createTitleLabel("Account"));
            add(accountNumberLabel);
            add(createTitleLabel("Balance"));
            add(balanceLabel);
        }

    private static JLabel createTitleLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }

    private static JLabel createValueLabel(String text)
    {
        JLabel label = new JLabel(text);
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
        return label;
    }

    public void updateName(String name)
    {
        nameLabel.setText(name);
    }

    @Override
    public void changeName(NameChangeEvent event)
    {
        SwingUtilities.invokeLater(()-> nameLabel.setText(event.getName()));
    }

    @Override
    public void changeBalance(BalanceChangedEvent event)
    {
        double balance = event.getBalance();
        SwingUtilities.invokeLater(() -> balanceLabel.setText(CurrencyHelper.getLocalCurrencyFormatter().format(balance)));
    }
}
