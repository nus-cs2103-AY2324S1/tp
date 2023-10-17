package seedu.address.ui;

import javafx.stage.Stage;
import seedu.address.model.person.Person;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /**
     * Shows the details of the specified person.
     *
     * @param person The specified person to show the details of.
     */
    void showPersonDetails(Person person);
}
