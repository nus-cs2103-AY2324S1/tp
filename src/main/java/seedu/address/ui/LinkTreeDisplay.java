package seedu.address.ui;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * The LinkTreeDisplay class is responsible for displaying the hierarchical structure of teams and their members
 * within a project. It utilizes JavaFX for rendering the graphical user interface.
 *
 * <p>This class extends the UiPart class and is typically instantiated to display team structures in the user
 * interface. It retrieves data from the Logic class to populate the team structures and their members.</p>
 *
 * <p>Each team is represented as a TeamStructureDisplay, which includes the team's name, leader, and developers.
 * Multiple TeamStructureDisplay components are added
 * to the LinkTreeDisplay to display all the teams within a project.</p>
 *
 * @see UiPart
 * @see TeamStructureDisplay
 */
public class LinkTreeDisplay extends UiPart<Region> {

    private static final String FXML = "LinkTreeDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(LinkTreeDisplay.class);

    @FXML
    private Label projectName;

    @FXML
    private HBox teamStructures;

    private Logic logic;
    private ObservableList<Team> teams;

    /**
     * Constructs a new instance of the LinkTreeDisplay class.
     *
     * <p>This constructor initializes the LinkTreeDisplay with the provided Logic instance and project name.
     * It sets up the graphical user interface for displaying the project's team structure and populates it with
     * team information retrieved from the Logic instance.</p>
     *
     * @param logic       The Logic instance used to retrieve team information and manage the application's data.
     * @param projectName The name of the project for which the team structure is being displayed.
     */
    public LinkTreeDisplay(Logic logic, String projectName) {

        super(FXML);
        this.logic = logic;
        this.teams = logic.getTeamBook().getTeamList();
        this.projectName.setText("Project name: " + projectName);

        addAllTeamStructure();

    }

    /**
     * Adds all team structures for the project to the display. This method iterates through the list of teams
     * retrieved from the Logic class and adds each team's structure to the UI.
     */
    public void addAllTeamStructure() {
        for (Team t : teams) {
            addOneTeamStructure(t);
        }
    }

    /**
     * Adds the structure of a single team to the display. This method takes a Team object as input and creates a
     * TeamStructureDisplay component to represent the team's name, leader, and developers. The resulting
     * TeamStructureDisplay is added to the UI.
     *
     * @param t The Team object representing the team to be added to the display.
     */
    public void addOneTeamStructure(Team t) {
        String teamName = t.getTeamName();
        IdentityCode teamLeaderID = t.getTeamLeaderIdentityCode();
        Person teamLeader = logic.getPersonByIdentityCode(teamLeaderID);

        Set<IdentityCode> developerIDs = t.getDeveloperIdentityCodes();
        ObservableList<Person> developers = FXCollections.observableArrayList();
        for (IdentityCode i : developerIDs) {
            developers.add(logic.getPersonByIdentityCode(i));
        }


        TeamStructureDisplay teamStructureDisplay = new TeamStructureDisplay(teamName, teamLeader, developers);
        this.teamStructures.getChildren().add(teamStructureDisplay.getRoot());
    }
}
