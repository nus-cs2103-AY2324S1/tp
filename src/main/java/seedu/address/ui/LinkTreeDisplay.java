package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.ReadOnlyTeamBook;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

import java.util.Set;
import java.util.logging.Logger;

public class LinkTreeDisplay extends UiPart<Region> {

    private static final String FXML = "LinkTreeDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(LinkTreeDisplay.class);

    @FXML
    private Label projectName;

    @FXML
    private HBox teamStructures;

    private Logic logic;
    private ObservableList<Team> teams;

    public LinkTreeDisplay(Logic logic, String projectName) {

        super(FXML);
        this.logic = logic;
        this.teams = logic.getTeamBook().getTeamList();
        this.projectName.setText("Project name: " + projectName);

        addAllTeamStructure();

    }


    public void addAllTeamStructure() {
        for (Team t : teams) {
            addOneTeamStructure(t);
        }
    }

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
