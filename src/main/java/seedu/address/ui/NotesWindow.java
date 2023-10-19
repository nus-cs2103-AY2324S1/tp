package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Controller for a notes window
 */
public class NotesWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(NotesWindow.class);
    private static final String FXML = "NotesWindow.fxml";

    @FXML
    private ListView<String> notesListView;

    private final Person person;

    /**
     * Creates a new NotesWindow.
     * @param root
     * @param person
     */
    public NotesWindow(Stage root, Person person) {
        super(FXML, root);
        this.person = person;
        populateListView(person.getNotes());
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

    private void populateListView(List<Note> notes) {
        ObservableList<String> notesObservableList = FXCollections.observableArrayList();
        for (Note note : notes) {
            notesObservableList.add(note.toString());
        }
        notesListView.setItems(notesObservableList);
    }
    @FXML
    void handleClose() {
        Stage stage = (Stage) notesListView.getScene().getWindow();
        stage.close();
    }

    public ListView<String> getNotesListView() {
        return notesListView;
    }

}
