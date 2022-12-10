package helicopter;

import utility.console.ConsoleMenu;
import java.util.ArrayList;

public class HelicopterMenu {
    
    //Private Data Members
    private final Helicopter helicopter;
    private final ConsoleMenu menu;

    // Helicopter Menu Constructor
    public HelicopterMenu(Helicopter helicopter){
       
        this.helicopter = helicopter;
      
        //Adding Options for Menu
        ArrayList<String> options = new ArrayList<>();
        options.add("> Start");
        options.add("> Stop");
        options.add("> Display");
        options.add("> Exit");

        //Creating Menu List
        menu = new ConsoleMenu("MENU", "Select an Option above : ", options);
    };

    public void start () {

        System.out.println(helicopter.displayHelicopter());

        while (true) {
            int selection = menu.displayAndGetSelection();

            if (selection == 1) {

                StartHelicopter();
            } 
            else if (selection == 2)
            {
                StopHelicopter();

            } else if (selection == 3) 
            {
                System.out.println(helicopter.displayHelicopter());

            } else if (selection == 4) {
                
                return;
                }
            }
        
    }

    public void StartHelicopter()
    {
        if(helicopter.canStartEngine())
        {
            System.out.println("Starting Engine");
            helicopter.startEngine();
            System.out.println(helicopter.displayHelicopter());
        }
        else if (helicopter.isEngineRunning())
        {
            System.out.println("Your engine is already running! ");
        }
        else
        {
            System.out.println("Error! Cannot Start Engine! ");
        }
    }

   public void StopHelicopter()
   {
       if(helicopter.canStopEngine())
       {
           System.out.println("Stopping Engine");
           helicopter.stopEngine();
           System.out.println(helicopter.displayHelicopter());
       } else if (!helicopter.isEngineRunning()) {
           System.out.println("Your engine is already stopped!");
       }
       else
       {
           System.out.println("Error! Cannot Stop Engine!");
       }
   }
}