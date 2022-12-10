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

                helicopter.startEngine();
            } 
            else if (selection == 2)
            {
                helicopter.stopEngine();

            } else if (selection == 3) 
            {
                System.out.println(helicopter.displayHelicopter());

            } else if (selection == 4) {
                
                return;
                }
            }
        
    }
}