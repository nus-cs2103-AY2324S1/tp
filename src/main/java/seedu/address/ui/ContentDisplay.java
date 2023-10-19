package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * The area for displaying content.
 */
public class ContentDisplay extends UiPart<Region> {
    private static final String FXML = "ContentDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(ContentDisplay.class);

    private Person selectedPerson;

    private ClientProfilePanel clientProfilePanel;

    @FXML
    private ListView<Person> personListView;
    @FXML
    private StackPane contentDisplayPlaceholder;
    @FXML
    private VBox clientProfilePanelPlaceholder;

    /**
     * Creates a {@code ContentDisplay} with the given {@code ObservableList}.
     */
    public ContentDisplay(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedPerson = newValue;
            clientProfilePanel = new ClientProfilePanel(selectedPerson);
            clientProfilePanelPlaceholder.getChildren().clear();
            clientProfilePanelPlaceholder.getChildren().add(clientProfilePanel.getRoot());
        });
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
