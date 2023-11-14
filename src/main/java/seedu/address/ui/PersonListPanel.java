package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final String fieldToRead;

    @FXML
    private ListView<Person> personListView;

    private List<Integer> indexes;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList} and list of indexes.
     */
    public PersonListPanel(ObservableList<Person> personList, List<Integer> indexes) {
        super(FXML);
        personListView.setItems(personList);
        if (indexes != null) {
            personListView.setCellFactory(listView -> new PersonListViewCell(indexes));
        } else {
            personListView.setCellFactory(listView -> new PersonListViewCell());
        }
        this.fieldToRead = "";
    }

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.fieldToRead = "";
    }

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, String fieldToRead) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new SpecifyPersonListViewCell());
        this.fieldToRead = fieldToRead;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        private List<Integer> indexes;
        public PersonListViewCell() {
            this.indexes = null;
        }

        public PersonListViewCell(List<Integer> indexes) {
            this.indexes = indexes;
        }
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (indexes != null) {
                    setGraphic(new PersonCard(person, getIndex() + 1, this.indexes).getRoot());
                } else {
                    setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                }
            }
        }
    }

    /**
     * Sets the list of indexes to be displayed.
     */
    public void indexesSetter(List<Integer> indexes) {
        this.indexes = indexes;
        if (indexes != null) {
            personListView.setCellFactory(listView -> new PersonListViewCell(indexes));
        } else {
            personListView.setCellFactory(listView -> new PersonListViewCell());
        }
        personListView.requestLayout();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of
     * a {@code Person} using a {@code PersonCardWithSpecificField}.
     */
    class SpecifyPersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCardWithSpecificField(person, getIndex() + 1, fieldToRead).getRoot());
            }
        }
    }
}
