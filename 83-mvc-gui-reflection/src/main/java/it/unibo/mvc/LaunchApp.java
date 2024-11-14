package it.unibo.mvc;

import java.lang.reflect.Constructor;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() {
    }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException    if the fetches class does not exist
     * @throws NoSuchMethodException     if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException    if the constructor throws exceptions
     * @throws IllegalAccessException    in case of reflection issues
     * @throws IllegalArgumentException  in case of reflection issues
     */
    private static final String[] VIEW_CLASS_NAMES = {
            "it.unibo.mvc.view.PrintNumberView",
            "it.unibo.mvc.view.PrintNumberView",
            "it.unibo.mvc.view.PrintNumberView",
            "it.unibo.mvc.view.PrintNumberView",
            "it.unibo.mvc.view.PrintNumberView",
            "it.unibo.mvc.view.PrintNumberView"
    };

    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        for (String viewClassName : VIEW_CLASS_NAMES) {
            try {
                // Load the class by name
                Class<?> viewClass = Class.forName(viewClassName);

                // Check if the class implements DrawNumberView interface
                if (DrawNumberView.class.isAssignableFrom(viewClass)) {

                    // Get the 0-argument constructor
                    Constructor<?> constructor = viewClass.getConstructor();

                    // Create an instance of the view
                    DrawNumberView viewInstance = (DrawNumberView) constructor.newInstance();

                    // Add the view to the controller
                    app.addView(viewInstance);
                } else {
                    System.err.println("Class " + viewClassName + " does not implement DrawNumberView.");
                }

            } catch (Exception e) {
                System.err.println("Failed to load view: " + viewClassName);
                e.printStackTrace();
            }
        }

        // Repeat the above loop for creating three command line views and three
        // graphical views
        // Here you might add each type multiple times if necessary
    }
}
