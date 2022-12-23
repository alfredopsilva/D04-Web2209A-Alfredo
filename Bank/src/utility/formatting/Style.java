package utility.formatting;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Style {

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
