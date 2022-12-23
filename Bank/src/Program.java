import bank.controllers.AccountController;
import bank.models.Account;
import utility.formatting.Style;
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
        //Create Model
        var account = new Account(12313,"Alfredo Silva", 500);

        // Create View
        Style.configureLookAndFeel();
        AccountView view = new AccountView(account);

        // Create Controller
        AccountController controller = new AccountController(account, view);

        // Create Window to display View.
        Window window = new Window ("Bank Of Developers", view, false);
        window.setSize(500,300);
        window.setVisible(true);
    }

}
