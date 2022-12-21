import bank.models.Account;
import utility.swing.windows.Window;
import bank.views.AccountView;

import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::start);
    }

    // Swing-related code should only be executed on Swing's dedicated Event Dispatch Thread(EDT).
    // It means that technically
    // there should be no swing-related code in the main method. Because the main method is executed by the main-thread,
    // not the EDP.
    private static void start()
    {
        var account = new Account(12313,"Alfredo Silva", 500);
        AccountView.configureLookAndFeel();
        AccountView contentPane = new AccountView(account);
        Window window = new Window ("Bank Of Developers", contentPane, true);
        window.setVisible(true);
    }

}
