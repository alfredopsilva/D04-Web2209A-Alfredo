package bath.models;

import java.util.Objects;

public final class Bath
{
    // TODO: Declare fields
    private static short nextId = 1;
    private short bathID;
    private double capacity;
    private double level;
    private boolean isRunning;
    private boolean isDraining;


    public Bath(double capacity) throws IllegalAccessException {
        // TODO: If capacity is invalid (not between 50 and 200 inclusively), throw a capacity exception
        validateCapacity(capacity);

        // TODO: Initialize fields
        this.capacity = capacity;
        this.bathID = nextId++;
        this.level = 0;
        this.isDraining = false;
        this.isRunning = false;

        // TODO: Create and start a daemon thread to run the updateLevel method
        //  Create a new thread using a method reference to implement Runnable
        //  Set the thread to be a daemon thread (so it won't keep your program running after closing all windows)
        //  Start the thread

        Thread levelUpdaterThread = new Thread(this::updateLevel);
        levelUpdaterThread.setDaemon(true);
        levelUpdaterThread.start();
    }

    // Method to be executed by level updater thread
    private void updateLevel()
    {
        // TODO: Repeatedly, while water is draining and/or running:
        //  Calculate level offset (amount to increase or decrease per half second)
        //      If draining, amount drained per second = 16.5
        //      If running, amount filled per second = 12
        //  Call setter method to set level based on current level and calculated offset
        //  Sleep thread for half a second (500 milliseconds)
        while(true) {
            sleepThread(0.500);
            if (isDraining && getCapacity() >= 0) {
                setLevel(6);
            } else {
                setLevel(-8.25);
            }
        }

    }

    private static void sleepThread(double seconds)
    {
        try
        {
            long milliseconds = (long)(seconds * 1000);
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }


    private void setLevel(double amount)
    {
        // TODO: If level parameter is actually different than current level:
        //  Change the current level, making sure that it doesn't go outside the valid range
        //  Notify each listener of a level changed event
        //  If bath is empty or full, automatically stop draining water and stop running water
        if(isRunning && level <= getCapacity())
        {
            this.level += amount;
        }
        else if (isDraining && level >= 0)
        {
            this.level -= amount;
        }
    }

    public int getId()
    {
        // TODO
        return bathID;
    }

    public double getCapacity()
    {
        // TODO
        return capacity;
    }

    public double getLevel()
    {
        // TODO
        return level;
    }

    public boolean isDrainingWater()
    {
        // TODO

        return getLevel() < level;
    }

    public boolean isRunningWater()
    {
        // TODO
        return false;
    }

    public boolean isEmpty()
    {
        return getLevel() != 0;
    }

    public boolean isFull()
    {
        // TODO
        return getLevel() == getCapacity();

    }

    public void addListener(IBathListener listener)
    {
        // TODO
    }

    public void removeListener(IBathListener listener)
    {
        // TODO
    }

    public void startDrainingWater() throws IllegalAccessException {
        // TODO: If water is already draining, throw a bath exception with a user-friendly error message
        // TODO: If bath is empty, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a drain changed event
        if(isDraining)
        {
            throw new IllegalAccessException("Your bath is already draining. Be patience!");
        } else if (isEmpty())
        {
            throw new IllegalAccessException("Your bath is already empty.");
        }
        isDraining = true;
       // updateLevel();
    }

    public void stopDrainingWater() throws IllegalAccessException {
        // TODO: If water is already not draining, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a drain changed event
        if(!isDraining)
        {
            throw new IllegalAccessException("Nothing happens here. The bath is already not draining");
        }
        isDraining = false;

    }

    public void startRunningWater() throws IllegalAccessException {
        // TODO: If water is already running, throw a bath exception with a user-friendly error message
        // TODO: If bath is full, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a faucet changed event
        if(isRunning)
        {
            throw new IllegalAccessException("Your bath is already running. Be patience! ");
        }
        isRunning = true;
        //updateLevel();
    }

    public void stopRunningWater() throws IllegalAccessException {
        // TODO: If water is already not running, throw a bath exception with a user-friendly error message
        // TODO: Perform action by changing bath state
        // TODO: Notify each listener of a faucet changed event
        if(isRunning)
        {
            throw new IllegalAccessException("Your bath is already running. Be patience! ");
        }
        isRunning = false;
    }

    private void validateCapacity(double capacity) throws IllegalAccessException {
        {
            if(capacity < 50 || capacity > 200)
            {
                throw new IllegalAccessException("Capacity needs to be between 50L and 200L");
            }
        }
    }
}
