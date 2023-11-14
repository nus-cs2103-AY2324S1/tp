package seedu.address.ui;

import javafx.stage.Stage;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

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

    /**
     * Shows the details of the specified lesson.
     *
     * @param lesson The specified lesson to show the details of.
     */
    void showLessonDetails(Lesson lesson);

    /**
     * Shows the details of the specified task.
     *
     * @param task The specified task to show the details of.
     */
    void showTaskDetails(Task task);

    /**
     * Sets the appropriate panels to display, hide all other panels.
     *
     * @param state The new state that determines which panels to show.
     */
    void changeLayout(State state);
}
