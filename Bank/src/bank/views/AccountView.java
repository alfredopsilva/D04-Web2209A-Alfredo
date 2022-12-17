package bank.views;

import utility.swing.components.InputField;
import utility.swing.components.LabelField;
import utility.swing.components.LabelFieldGrid;
import utility.swing.components.StringFieldData;
import utility.swing.layout.LayoutHelper;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AccountView extends JPanel {

    private final LabelFieldGrid fields;

    public AccountView()
    {
        ArrayList<StringFieldData> fields = new ArrayList<>();
        fields.add(new StringFieldData("Name", "Anna"));
        fields.add(new StringFieldData("Account", "1000001"));
        fields.add(new StringFieldData("Balance", "$500,00"));
        this.fields = new LabelFieldGrid(fields);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(LayoutHelper.createLargeEmptyBorder());

        JLabel label = new JLabel("Enter an amount");
        label.setAlignmentX(0.5f);
        InputField amount = new InputField("Amount");

        setBorder(LayoutHelper.createEmptyBorder());
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");

        JPanel buttonSection = new JPanel();
        buttonSection.add(deposit);
        buttonSection.add(withdraw);
        buttonSection.setLayout(new FlowLayout());


        add(this.fields);
        add(LayoutHelper.createLargeRigidArea());
        add(label);
        add(LayoutHelper.createLargeRigidArea());
        add(amount);
        add(LayoutHelper.createLargeRigidArea());
        add(buttonSection);

    }
}
