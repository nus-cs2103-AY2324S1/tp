package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;


/**
 * The TeamStructureDisplay class is responsible for displaying the structure of a team within a project.
 * It utilizes JavaFX for rendering the graphical user interface.
 *
 * <p>This class extends the UiPart class and is typically instantiated to display a team's name, leader, and
 * list of developers in the user interface. It provides a visual representation of the team's structure.</p>
 *
 * <p>The DevelopersViewCell inner class is a custom ListCell used for rendering each developer's information within
 * the ListView of developers. It displays developer information using a PersonCard component.</p>
 *
 * @see UiPart
 * @see DevelopersViewCell
 */
public class TeamStructureDisplay extends UiPart<Region> {
    private static final String FXML = "TeamStructureDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(TeamStructureDisplay.class);


    @FXML
    private Label teamName;

    @FXML
    private Label teamLeader;
    @FXML
    private ListView<Person> developerListView;

    /**
     * Constructs a new instance of the TeamStructureDisplay class.
     *
     * <p>This constructor initializes the TeamStructureDisplay with the provided team name, team leader,
     * and a list of developers. It sets up the graphical user interface for displaying the structure of a team
     * within a project, including the team's name, leader, and list of developers.</p>
     *
     * @param teamName       The name of the team being displayed.
     * @param teamLeader     The leader of the team being displayed.
     * @param developers     An ObservableList of Person objects representing the developers in the team.
     */
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
