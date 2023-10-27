package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

public class TeamStructureDisplay extends UiPart<Region> {
    private static final String FXML = "TeamStructureDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(TeamStructureDisplay.class);


    @FXML
    private Label teamName;

    @FXML
    private Label teamLeader;
    @FXML
    private ListView<Person> developerListView;


    public TeamStructureDisplay(String teamName, Person teamLeader, ObservableList<Person> developers) {
        super(FXML);

        this.teamName.setText("Team Name: " + teamName);
        this.teamLeader.setText("Team Leader:\n" + teamLeader.getName());

        developerListView.setItems(developers);
        developerListView.setCellFactory(listView -> new DevelopersViewCell());

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class DevelopersViewCell extends ListCell<Person> {
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
