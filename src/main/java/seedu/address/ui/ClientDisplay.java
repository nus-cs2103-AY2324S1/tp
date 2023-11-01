package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * The area for displaying content.
 */
public class ClientDisplay extends UiPart<Region> {
    private static final String FXML = "ClientDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(ClientDisplay.class);

    private ClientProfilePanel clientProfilePanel;

    @FXML
    private ListView<Person> personListView;
    @FXML
    private VBox clientProfilePanelPlaceholder;

    /**
     * Creates a {@code ClientDisplay} with the given {@code personList} and {@code selectedPerson}.
     */
    public ClientDisplay(ObservableList<Person> personList, SimpleObjectProperty<Person> selectedPerson) {
        super(FXML);

        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedPerson.setValue(newValue);
        });

        viewPerson(selectedPerson.getValue());

        selectedPerson.addListener((observable, oldValue, newValue) -> {
            clientProfilePanelPlaceholder.getChildren().clear();

            viewPerson(newValue);
        });
    }

    /**
     * Displays the profile of the given {@code person}.
     */
    void viewPerson(Person person) {
        if (person == null) {
            return;
        }

        clientProfilePanel = new ClientProfilePanel(person);
        clientProfilePanelPlaceholder.getChildren().add(clientProfilePanel.getRoot());

        // set focus within the list if the change is from a `view` command
        personListView.getSelectionModel().select(person);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
