package seedu.address.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 * A utility class for initializing and cleaning up JavaFX for testing.
 */
public class JavaFxInitializer {

    private static boolean initialized = false;

    /**
     * Initializes JavaFX on a separate thread.
     */
    public static void initialize() {
        if (!initialized) {
            new JFXPanel(); // Initializes JavaFX environment
            initialized = true;
        }
    }

    /**
     * Cleans up the JavaFX initialization if needed.
     */
    public static void cleanup() {
        if (initialized) {
            Platform.exit(); // Exits JavaFX environment
            initialized = false;
        }
    }

    /**
     * A JavaFX Application class used for initializing JavaFX.
     */
    public static class JavaFxInit extends Application {
        @Override
        public void start(Stage primaryStage) {
            // JavaFX initialization logic
        }
    }
}
