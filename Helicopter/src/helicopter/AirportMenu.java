package helicopter;

import java.util.ArrayList;
import utility.console.ConsoleMenu;

public class AirportMenu {
    private final ArrayList<Helicopter> helicopters;
    private final ConsoleMenu AirportMenu;
    public AirportMenu()
    {
        // Adding 5 Helicopters to the ArrayList and *technical name for the 16 line*
        helicopters = new ArrayList<>();
        helicopters.add(new Helicopter());
        helicopters.add(new Helicopter());
        helicopters.add(new Helicopter());
        helicopters.add(new Helicopter());
        helicopters.add(new Helicopter());

        // Adding Options for Menu
        ArrayList<String> options = new ArrayList<String>();
        options.add("> Add Helicopter");
        options.add("> Remove Helicopter");
        options.add("> Control Helicopter");
        options.add("> Land All Helicopters");
        options.add("> Display All Helicopter");
        options.add("> Exit");

        //Creating Menu List
        AirportMenu = new ConsoleMenu("AIRPORT MENU", "Select an Option above : ", options);
    }

   public void start() {

        displayAllHelicopters();
        while(true)
        {
            int selection = AirportMenu.displayAndGetSelection();
            switch(selection) {
                case 1: addHelicopter();          break;
                case 2: removeHelicopter();       break;
                case 3: ;            break;
                case 4: ;              break;
                case 5: ;          break;
                case 6: return;
            }
            displayAllHelicopters();
        }
    }

    public void addHelicopter()
    {
        helicopters.add(new Helicopter());
    }

    public void removeHelicopter()
    {
        ArrayList<String> options = new ArrayList<String>();
        for(Helicopter helicopter : helicopters) {
            options.add(helicopter.toString());
        }
        options.add("Exit");

        ConsoleMenu HelicopterMenu = new ConsoleMenu("Remove an Helicopter", "Select One Helicopter:", options);
        int index = HelicopterMenu.displayAndGetSelection() - 1;
    }



    public void controlHelicopter()
    {

    }

    public void landAllHelicopter()
    {

    }

    public void displayAllHelicopters()
    {
        for(Helicopter helicopter : helicopters)
        {
            System.out.println(helicopter.displayHelicopter());
        }
    }

}
