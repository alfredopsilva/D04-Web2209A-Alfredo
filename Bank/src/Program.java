import utility.swing.windows.Window;
import bank.views.AccountView;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Program {
    public static void main(String[] args) {

        configureLookAndFeel();
        AccountView contentPane = new AccountView();
        Window window = new Window ("Bank Of Developers", contentPane, true);
        window.setVisible(true);

    }

    private static void configureLookAndFeel() // Can change some settings from the interface.
    {
        useSystemLookAndFeel();

        Color backgroundColor = Color.white;
        ColorUIResource backgroundColorResource = new ColorUIResource(backgroundColor);
        UIManager.put("Panel.background", backgroundColorResource);
        UIManager.put("OptionPane.background", backgroundColorResource);
    }

    private static void useSystemLookAndFeel() // Its responsible to change de interface.
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

}
