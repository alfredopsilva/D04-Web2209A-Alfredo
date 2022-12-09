package helicopter;

import utility.console.ConsoleMenu;

import java.util.ArrayList;

public class HelicopterMenu {

    Helicopter helicopter;
    ConsoleMenu menu;


    public HelicopterMenu(Helicopter helicopter){
        this.helicopter = helicopter;
        ArrayList<String> options = new ArrayList<>();
        options.add("Display");
        options.add("Exit");
        menu = new ConsoleMenu("MENU", "Select an Option above : ", options);
    };

    public void start (){

        while(true) {

            int selection = menu.displayAndGetSelection();
            if (selection == 1) {
                System.out.println(helicopter.toString());
            } else if (selection == menu.getSize()) {
                return;
            }
        }
    };


}
