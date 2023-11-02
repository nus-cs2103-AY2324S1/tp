package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML private TableView<Person> table;
    @FXML private TableColumn<Person, Void> indexCol;
    @FXML private TableColumn<Person, Void> contactCol;
    @FXML private TableColumn<Person, Void> notesCol;
    @FXML private TableColumn<Person, Void> eventsCol;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);

        indexCol.setCellFactory(col -> new TableIndexCell());
        indexCol.setPrefWidth(50);

        contactCol.setCellFactory(col -> new TableContactsCell(personList));

        notesCol.setCellFactory(col -> new TableNotesCell(personList));
        notesCol.minWidthProperty().bind(
            table.widthProperty().multiply(0.5));

        eventsCol.setCellFactory(col -> new TableEventsCell(personList));
        eventsCol.minWidthProperty().bind(
            table.widthProperty().multiply(0.3));

        table.setItems(personList);
    }

    class TableContactsCell extends TableCell<Person, Void> {
        private ObservableList<Person> personList;

        public TableContactsCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                setGraphic(new ContactCard(personList.get(index)).getRoot());
            }
        }
    }

    class TableNotesCell extends TableCell<Person, Void> {
        private ObservableList<Person> personList;

        public TableNotesCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                ListView<Note> notes = new ListView<>();
                notes.setItems(personList.get(index).getNotes());
                notes.setCellFactory(cell -> new NoteCell());
                notes.setPrefHeight(120);
                setGraphic(notes);
            }
        }
    }

    class TableEventsCell extends TableCell<Person, Void> {
        private ObservableList<Person> personList;

        public TableEventsCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                ListView<Event> events = new ListView<>();
                events.setItems(personList.get(index).getEvents());
                events.setCellFactory(cell -> new EventCell());
                events.setPrefHeight(120);
                setGraphic(events);
            }
        }
    }

    class TableIndexCell extends TableCell<Person, Void> {
        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setText(null);
            } else {
                setText(Integer.toString(index + 1));
            }
        }
    }

    private class NoteCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);
            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText((getIndex() + 1) + ". " + note.getUiText());
                setTextFill(Color.WHITE);
            }
        }
    }

    private class EventCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);
            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText((getIndex() + 1) + ". " + event.getUiText());
                setTextFill(Color.WHITE);
            }
        }
    }
}
