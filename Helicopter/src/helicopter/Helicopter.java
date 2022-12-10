package helicopter;

public class Helicopter {

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
    public String getId(){
        return id;
    }
    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getAltitude() {
        return altitude;
    }

    public boolean isEngineRunning() {
        return engineRunning;
    }

    @Override
    public String toString(){
        return "Helicopter #" + id;
    }
}


