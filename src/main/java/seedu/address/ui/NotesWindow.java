package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Controller for a notes window
 */
public class NotesWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(NotesWindow.class);
    private static final String FXML = "NotesWindow.fxml";

    @FXML
    private TextArea notesTextArea;

    private final Person person;

    /**
     * Creates a new NotesWindow.
     * @param root
     * @param person
     */
    public NotesWindow(Stage root, Person person) {
        super(FXML, root);
        this.person = person;
        notesTextArea.setText(person.getNotes().toString());
    }

    public NotesWindow(Person person) {
        this(new Stage(), person);
    }

    /**
     * Shows the notes window.
     */
    public void show() {
        logger.fine("Showing notes for " + person.getName().fullName);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void hide() {
        getRoot().hide();
    }

    public void focus() {
        getRoot().requestFocus();
    }

    // Other potential methods for interactions with notes (e.g., saving, deleting, etc.)

}
