package seedu.application.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.application.model.job.Job;

/**
 * An UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Job job;

    @FXML
    private HBox cardPane;
    @FXML
    private Label role;
    @FXML
    private Label id;
    @FXML
    private Label company;
    @FXML
    private FlowPane status;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code JobCard} with the given {@code Job} and index to display.
     */
    public JobCard(Job job, int displayedIndex) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        role.setText(job.getRole().description);
        company.setText(job.getCompany().name);
        Label statusLabel = setStatusLabelStyle();
        status.getChildren().add(statusLabel);
        deadline.setText(job.getDeadline().deadline);
    }

    private Label setStatusLabelStyle() {
        String jobStatus = job.getStatus().status;
        Label statusLabel = new Label(jobStatus);
        statusLabel.getStyleClass().add("cell_status_label");

        if (jobStatus.equals("TO_BE_SUBMITTED")) {
            statusLabel.getStyleClass().add("to_be_submitted");
        } else if (jobStatus.equals("PENDING")) {
            statusLabel.getStyleClass().add("pending");
        } else if (jobStatus.equals("APPROVED")) {
            statusLabel.getStyleClass().add("approved");
        } else if (jobStatus.equals("REJECTED")) {
            statusLabel.getStyleClass().add("rejected");
        }
        return statusLabel;
    }
}
