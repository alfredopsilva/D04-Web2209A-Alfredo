package helicopter;

public class Helicopter {

    // Final Data-Members

    private static final int MAX_ALTITUDE = 5000;
    private static final int MIN_ALTITUDE = 0;
    private static final int MAX_FUEL_RANGE = 100;
    private static final int MIN_FUEL_RANGE = 0;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    //Private Data-Members
     private static int nextId = 82;
     private String id = "HEP12";
     private double fuelLevel ;
     private double altitude ;
     private boolean engineRunning = false;
    private boolean exploded;

    // Constructor

    public Helicopter(){
        this.id = id + nextId++;
        this.fuelLevel = MAX_FUEL_RANGE;
        this.altitude = 0;
        this.engineRunning = false;
        this.exploded = false;
    };

    // Getters
    public String getId()
    {
        return id;
    }
    public double getFuelLevel()
    {
        return fuelLevel;
    }
    public double getAltitude()
    {
        return altitude;
    }
    public boolean isEngineRunning()
    {
        return engineRunning;
    }
    public boolean isExploded() { return exploded; };
    public double getFuelRate()
    {
        return 1.00 / 100.00;
    }
    public boolean isFuelEmpty()
    {
        return getFuelLevel() == MIN_FUEL_RANGE;
    }

    public boolean isFuelFull()
    {
        return getFuelLevel() == MAX_FUEL_RANGE;
    }

    public boolean isLanded()
    {
        return getAltitude() == MIN_ALTITUDE;
    }

    public boolean isFlying()
    {
        return getAltitude() > MIN_ALTITUDE;
    }

    // Setters
    public void setFuelLevel(double amount)
    {
        if(fuelLevel < MIN_FUEL_RANGE)
            fuelLevel = MIN_FUEL_RANGE;
        else if(fuelLevel > MAX_FUEL_RANGE)
            fuelLevel = MAX_FUEL_RANGE;
        this.fuelLevel = amount;

        if(isFuelEmpty() && isFlying())
        {
            crash();
        }
    }

    // Can-Do-Action Getters
    public boolean canStartEngine()
    {
        return !engineRunning
                && isLanded()
                && !isFuelEmpty()
                && !exploded;
    }

    public void startEngine(){
        if(canStartEngine())
        {
            engineRunning = true;
        }
    }

    public boolean canStopEngine()
    {
        return isEngineRunning()
                && isLanded()
                && !exploded;
    }
    public void stopEngine()
    {
        if(canStopEngine())
            engineRunning = false;
    }

    public boolean canFly(){

        return isEngineRunning()
                && !exploded
                && !isFuelEmpty();

    }
    public void flyToAltitude(double altitude)
    {
         if(canFly())
         {
             if(altitude < MIN_ALTITUDE)
                 altitude = MIN_ALTITUDE;
             else if(altitude > MAX_ALTITUDE)
                 altitude = MAX_ALTITUDE;
            double offset = altitude - this.altitude;
            double distance = Math.abs(offset);
            double fuelBurned = distance * getFuelRate();
         }
         this.altitude = altitude;
    }

    public void landing()
    {
        this.altitude = 0;
    }

    public boolean canRefuel(double amount)
    {
        return !engineRunning
                && !exploded
                && isLanded()
                && !isFuelFull()
                && amount > 0;
    }
    public void refuel (double amount)
    {
        if(canRefuel(amount))
        {
            double maxAmount = MAX_FUEL_RANGE - fuelLevel;
            if(amount > maxAmount)
                amount = maxAmount;
            setFuelLevel(fuelLevel + amount);
        }
    }

    private void crash()
    {

    }


    // To String method
    @Override
    public String toString()
    {
        return "\nHelicopter #" + id;
    }

    public String displayHelicopter() // Why this method is not Override?
    { 
        return toString()
               + ": " + (isEngineRunning() ? "Engine" + ANSI_GREEN + " ON" + ANSI_RESET:
                                              "Engine" + ANSI_RED + " OFF" + ANSI_RESET)
               + ", " + "Altitude = " + altitude
               + ", " + "Fuel Level = " + fuelLevel
                + ", " + (exploded ? "(Exploded)" : "(Functional)");

    }
}


