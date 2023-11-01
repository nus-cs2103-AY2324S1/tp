package seedu.letsgethired.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.InternApplication;

/**
 * An UI component that displays information of a {@code InternApplication}.
 */
public class InternApplicationCard extends UiPart<Region> {

    private static final String FXML = "InternApplicationListCard.fxml";
    private static final String ACCEPTED_COLOR = "-fx-background-color: #7e38b7;";
    private static final String ASSESSMENT_COLOR = "-fx-background-color: #ffd100;";
    private static final String INTERVIEW_COLOR = "-fx-background-color: #fd5602;";
    private static final String OFFERED_COLOR = "-fx-background-color: #03C04A;";
    private static final String PENDING_COLOR = "-fx-background-color: #209cee;";
    private static final String REJECTED_COLOR = "-fx-background-color: #d30000;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on InternTracker level 4</a>
     */

    public final InternApplication internApplication;

    private final CommandExecutor commandExecutor;
    private int indexNum;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label company;
    @FXML
    private Label role;
    @FXML
    private Label status;
    @FXML
    private Label cycle;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code InternApplicationCard} with the given {@code internApplication} and index to display.
     */
    public InternApplicationCard(InternApplication internApplication,
                                 int displayedIndex,
                                 CommandExecutor commandExecutor) {
        super(FXML);
        this.internApplication = internApplication;
        this.commandExecutor = commandExecutor;
        this.indexNum = displayedIndex;
        id.setText(displayedIndex + ". ");
        company.setText(internApplication.getCompany().value);
        role.setText(internApplication.getRole().value);
        cycle.setText(internApplication.getCycle().value);
        deadline.setText(internApplication.getDeadline().value);
        setStatusText(internApplication.getStatus().value);
        setStatusColor(internApplication.getStatus().value);
    }

    /**
     * Gets the status label color coded by the internship application status.
     *
     * @return the status label color coded by the internship application status.
     */
    private void setStatusText(String statusString) {
        String standardFormatStatus = statusString.substring(0, 1).toUpperCase()
                + statusString.substring(1).toLowerCase();
        status.setText(standardFormatStatus);
    }

    private void setStatusColor(String statusString) {
        String standardFormatStatus = statusString.substring(0, 1).toUpperCase()
                + statusString.substring(1).toLowerCase();
        switch (standardFormatStatus) {
        case "Interview":
            status.setStyle(INTERVIEW_COLOR);
            break;
        case "Accepted":
            status.setStyle(ACCEPTED_COLOR);
            break;
        case "Offered":
            status.setStyle(OFFERED_COLOR);
            break;
        case "Rejected":
            status.setStyle(REJECTED_COLOR);
            break;
        case "Assessment":
            status.setStyle(ASSESSMENT_COLOR);
            break;
        case "Pending":
            status.setStyle(PENDING_COLOR);
            break;
        default:
            break;
        }
    }

    /**
     * Displays card details on the SelectView
     */
    @FXML
    public void handleCardClick() {
        String commandText = "view " + this.indexNum;
        try {
            commandExecutor.execute(commandText);
        } catch (CommandException | ParseException e) {
            //should never reach here unless the hard-coded input is wrong
            assert false : "The program should never reach this block";
        }
    }
}
