package helicopter;

import utility.console.ConsoleMenu;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        options.add("> Auto Fly Up");
        options.add("> Fly Up");
        options.add("> Auto Fly Down");
        options.add("> Fly Down");
        options.add("> Land");
        options.add("> Refuel");
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
                autoFlyUp();

            }
            else if (selection == 4)
            {
                flyUp();

            }
            else if (selection == 5)
            {
                autoFlyDown();

            }
            else if (selection == 6)
            {
                flyDown();

            }
            else if (selection == 7)
            {
                land();

            }
            else if (selection == 8)
            {
                refuel();

            }
            else if (selection == 9)
            {
                System.out.println(helicopter.displayHelicopter());

            }
            else if (selection == 10) {
                
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

   public void autoFlyUp()
   {
        double autoAltitude = generatingRandomAltitude();
        double newAltitude = helicopter.getAltitude() + autoAltitude;
        if(helicopter.canFlyToAltitude(newAltitude))
        {
            System.out.println("Distance requested .: " + autoAltitude);
            System.out.println("FLLLLYIIIIING UP !!!");
            helicopter.flyToAltitude(newAltitude);
            System.out.println("Actual Altitude .: " + helicopter.getAltitude());
        }
   }

   public void flyUp()
   {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the distance: ");
       double userAltitude = scanner.nextDouble();
       double newAltitude = helicopter.getAltitude() + userAltitude;
       if(helicopter.canFlyToAltitude(newAltitude))
       {
           System.out.println("FLLLLYIIIIING UP !!!");
           helicopter.flyToAltitude(newAltitude);
           System.out.println("Actual Altitude .: " + helicopter.getAltitude());
       }

   }

   public void autoFlyDown()
   {
       double autoAltitude = generatingRandomAltitude();
       double newAltitude = helicopter.getAltitude() - autoAltitude;
       if(helicopter.canFlyToAltitude(newAltitude))
       {
           System.out.println("Distance requested .: " + autoAltitude);
           System.out.println("FLLLLYIIIIING UP !!!");
           helicopter.flyToAltitude(newAltitude);
           System.out.println("Actual Altitude .: " + helicopter.getAltitude());
       }
   }

   public void flyDown()
   {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the distance: ");
       double userAltitude = scanner.nextDouble();
       double newAltitude = helicopter.getAltitude() - userAltitude;
       if(helicopter.canFlyToAltitude(newAltitude))
       {

           System.out.println("FLLLLYIIIIING DOWN !!!");
           helicopter.flyToAltitude(newAltitude);
           System.out.println("Actual Altitude .: " + helicopter.getAltitude());
       }
   }

   public void land()
   {
       if(helicopter.canFlyToAltitude(0))
       {
           System.out.println("Initiating Landing Procedure");
           helicopter.landing();
           System.out.println("Helicopter Landed!!");
       } else if (helicopter.getAltitude() == 0) {

           System.out.println("This helicopter is already landed.");

       }
       else
       {
           System.out.println("Some error Happened at landing procedure!");
       }
   }

   public void refuel()
   {

   }

   public int generatingRandomAltitude()
   {
       Random random = new Random();
       int low = 400;
       int high = 500;
       int altitude = random.nextInt(high-low) + low;
       return altitude;
   }
}