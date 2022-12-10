package helicopter;

public class Helicopter {

    // Final Data-Members

    private static final int MAX_ALTITUDE = 5000;
    private static final int MIN_ALTITUDE = 0;
    private static final int MAX_FUEL_RANGE = 100;
    private static final int MIN_FUEL_RANGE = 0;


    //Private Data-Members
     private static int nextId = 82;
     private String id = "HEP12";
     private int fuelLevel ;
     private int altitude ;
     private boolean engineRunning = false;

    // Constructor

    public Helicopter(){
        this.id = id + nextId++;
        this.fuelLevel = 100;
        this.altitude = 0;
        this.engineRunning = false;
    };

    // Getters
    public String getId()
    {
        return id;
    }
    public int getFuelLevel()
    {
        return fuelLevel;
    }
    public int getAltitude()
    {
        return altitude;
    }
    public boolean isEngineRunning()
    {
        return engineRunning;
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

    // Can-Do-Action Getters
    public boolean canStartEngine()
    {
        return !engineRunning
                && isLanded()
                && !isFuelEmpty();
    }

    public void startEngine(){
        if(canStartEngine())
        {
            engineRunning = true;
        }
    }

    public boolean canStopEngine()
    {
        return engineRunning
                && isLanded();
    }

    public void stopEngine()
    {
        if(canStopEngine())
            engineRunning = false;
    }

    // To String method
    @Override
    public String toString()
    {
        return "Helicopter #" + id;
    }

    public String displayHelicopter() // Why this method is not Override?
    { 
        return toString()
               + ": " + (isEngineRunning() ? "Engine on" : "Engine off")
               + ", " + "Altitude = " + altitude
               + ", " + "Fuel Level = " + fuelLevel;

    }
}


