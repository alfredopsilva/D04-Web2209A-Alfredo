package bath.controllers;

import bath.models.Bath;
import bath.views.BathView;
import bath.views.SetupView;
import utility.swing.windows.Window;

import java.awt.event.ActionEvent;

/**
 * Do not modify this class, except for the input event handler method definition.
 * @author Jared Chevalier
 */
public final class SetupController
{
    private final SetupView view;
    private final Window window;
    private  Window bathWindow;


    public SetupController(SetupView view, Window window)
    {
        this.view = view;
        this.window = window;
        bathWindow = null;

        view.addCreateListener(this::onCreateClicked);
        window.getRootPane().setDefaultButton(view.getDefaultButton());
    }

    private void onCreateClicked(ActionEvent event) {
        // TODO
        //  Create bath model + view + controller

        Bath bath = null;
        try {
            bath = new Bath(Double.parseDouble(view.getCapacity()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BathView bathView = new BathView(bath);
        BathController bathController = new BathController(bath, bathView);
        window.dispose();
        bathWindow = new Window("Bath", bathView, true);
        bathWindow.setVisible(true);

        //  Create bath logger and add it as a bath listener
        //  Create bath client and add it as a bath listener
        //  Display bath view in window (to do so, either reuse current window, or create a new one and close current one)
        //  Handle possible exceptions thrown above by displaying error message in view
    }



}
