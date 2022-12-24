package bath.controllers;

import bath.models.Bath;
import bath.views.BathView;
import bath.views.SetupView;
import utility.swing.windows.Window;

import java.awt.event.ActionEvent;
import java.util.Objects;


public final class BathController
{
    private final Bath bath;
    private final BathView bathView;
    private SetupController setupController;



    public BathController(Bath bath, BathView bathView) {
        this.bath = Objects.requireNonNull(bath);
        this.bathView = Objects.requireNonNull(bathView);
        this.setupController = null;

        bathView.addStartDrainingListener(this::startDrainingClicked);
        bathView.addStopDrainingListener(this::stopDrainingClicked);
        bathView.addStartRunningListener(this::startRunningClicked);
        bathView.addStopRunningListener(this::stopRunningClicked);
        bathView.addExitListener(this::onExitClicked);
        bathView.addSetupListener(this::onSetupClicked);

    }

    // TODO
    //  Define input handler methods for each button in the view
    //  Do not forget to configure model and view in constructor by adding listeners to model and view

    // TODO: Input handlers for buttons: Start running + Stop running + Start draining + Stop draining
    //  Try to call corresponding action method on model
    //  If successful, display success message in view
    //  If failed, display error message in view

    // TODO: Input handler for button: Setup
    //  Create a setup view + controller, and display in a new window (same as on program startup)
    //  Close the current window (dispose window)
    private void onSetupClicked(ActionEvent actionEvent)
    {
        try {
            System.out.println("Setup Clicked");
            SetupView view = new SetupView();
            Window window = new Window("Setup", view, false);
            setupController = new SetupController(view, window);
            bathView.setVisible(false);
            window.setVisible(true);
        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));
        }

    }

    // TODO: Input handler for button: Exit
    //  Close the current window (dispose window)

    private void onExitClicked(ActionEvent actionEvent)
    {
        try
        {
            System.out.println("Exit Clicked");
            bathView.setVisible(false);

        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));
        }
    }

    private void startDrainingClicked(ActionEvent actionEvent)
    {
        try
        {
            System.out.println("Start Draining Clicked");
            bathView.displayMessage("Starting Draining Bath");
            bath.startDrainingWater();

        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));;
        }
    }private void stopDrainingClicked(ActionEvent actionEvent)
    {
        try
        {
            System.out.println("Stop Draining Clicked");
            bathView.displayMessage("Stopping Draining Bath");
            bath.stopDrainingWater();

        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));
        }
    }private void startRunningClicked(ActionEvent actionEvent)
    {
        try
        {
            System.out.println("Start Running Clicked");
            bathView.displayMessage("Starting Running Bath");
            bath.startRunningWater();

        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));
        }
    }private void stopRunningClicked(ActionEvent actionEvent)
    {
        try
        {
            System.out.println("Stop Running Clicked");
            bathView.displayMessage("Stopping Running Bath");
            bath.stopRunningWater();

        }
        catch (Exception e)
        {
            bathView.displayMessage(String.valueOf(e));
        }
    }
}
