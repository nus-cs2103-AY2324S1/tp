package seedu.address.ui;

import java.util.Set;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;


/**
 * A UI component that displays information of a {@code Team}.
 */
public class TeamCard extends UiPart<Region> {

    private static final String FXML = "TeamListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Team team;
    private ObservableList<Person> memberList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamName;
    @FXML
    private Label id;
    @FXML
    private VBox teamLeader;
    @FXML
    private VBox teamMembers;

    /**
     * Constructor of the TeamCard class which creates a
     * UI component that displays information of a {@code Team}.
     *
     * @param team On of the teams for display.
     * @param displayedIndex Index of the team for display.
     */
    public TeamCard(Team team, int displayedIndex, ObservableList<Person> memberList) {
        super(FXML);
        this.team = team;
        this.memberList = memberList;
        id.setText(displayedIndex + ".     ---- ");
        teamName.setText(team.getTeamName() + "  ----");

        IdentityCode leaderID = team.getTeamLeaderIdentityCode();
        Person leader = findPersonById(memberList, leaderID);
        if (leader != null) {
            Label teamLeaderLabel = new Label("Team leader ->  "
                    + leader.getName());
            teamLeaderLabel.setStyle("-fx-font-size: 14px;");
            teamLeaderLabel.setWrapText(true);
            teamLeaderLabel.setMaxWidth(800);
            teamLeader.getChildren().addAll(teamLeaderLabel);
        } else {
            // Handle the case where the leader is not found
            Label errorLabel = new Label("Team leader not found!");
            errorLabel.setStyle("-fx-font-size: 14px;");
            errorLabel.setWrapText(true);
            errorLabel.setMaxWidth(800);
            teamLeader.getChildren().addAll(errorLabel);
        }

        Label devLabel = new Label("Developers: ");
        teamMembers.getChildren().add(devLabel);
        Set<IdentityCode> developerIdentityCodes = team.getDeveloperIdentityCodes();
        if (developerIdentityCodes.isEmpty()) {
            Label memberLabel = new Label("( There is no developer in this team yet )");
            memberLabel.setStyle("-fx-font-size: 12px;");
            memberLabel.setWrapText(true);
            memberLabel.setMaxWidth(800);
            teamMembers.getChildren().add(memberLabel);
        } else {
            developerIdentityCodes.forEach(memberCode -> {
                Label memberLabel = new Label(" ->  "
                        + findPersonById(memberList, memberCode).getName());
                memberLabel.setStyle("-fx-font-size: 12px;");
                memberLabel.setWrapText(true);
                memberLabel.setMaxWidth(800);
                teamMembers.getChildren().add(memberLabel);
            });
        }
    }

    /**
     * Find the person in the address book given his IdentityCode
     *
     * @param personsList the list of persons in the address book
     * @param targetID the target person's IdentityCode
     * @return the Person of the given IdentityCode
     */
    public Person findPersonById(ObservableList<Person> personsList, IdentityCode targetID) {
        return personsList.stream()
                .filter(person -> person.getIdentityCode().equals(targetID))
                .findFirst()
                .orElse(null);
    }
}
