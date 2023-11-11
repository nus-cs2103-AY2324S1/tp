package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private ListView<Note> notesListView;

    private final Person person;

    /**
     * Creates a new NotesWindow.
     *
     * @param root Stage to use as the root of the NotesWindow.
     * @param person Person to display notes for.
     */
    public NotesWindow(Stage root, Person person) {
        super(FXML, root);
        this.person = person;
        populateListView(person.getNotes());

        // Add event filter to listen for ESC key press
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                handleClose();
            }
        });
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

    private void populateListView(ObservableList<Note> notes) {
        ObservableList<Note> notesObservableList = FXCollections.observableArrayList(notes);
        notesListView.setItems(notesObservableList);
        notesListView.setCellFactory(listView -> new ListCell<Note>() {
            @Override
            protected void updateItem(Note note, boolean empty) {
                super.updateItem(note, empty);
                if (empty || note == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = new Label((getIndex() + 1) + ". " + note.toString());
                    label.setWrapText(true);
                    label.prefWidthProperty().bind(listView.widthProperty().subtract(40));
                    setGraphic(label);
                }
            }
        });

        // make sure the width is always correct even after resizing the window
        notesListView.widthProperty().addListener((observable) -> {
            notesListView.refresh();
        });

        // Observe the person's notes for changes
        this.person.addNotesListener((ListChangeListener.Change<? extends Note> c) -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    populateListView(person.getNotes());
                }
            }
        });
    }

    @FXML
    void handleClose() {
        Stage stage = (Stage) notesListView.getScene().getWindow();
        stage.close();
    }

    public ListView<Note> getNotesListView() {
        return notesListView;
    }
}
