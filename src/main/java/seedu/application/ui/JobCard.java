package seedu.application.ui;

import static seedu.application.model.job.Status.JobStatus.APPROVED;
import static seedu.application.model.job.Status.JobStatus.PENDING;
import static seedu.application.model.job.Status.JobStatus.REJECTED;
import static seedu.application.model.job.Status.JobStatus.TO_BE_SUBMITTED;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.application.model.job.Job;
import seedu.application.model.job.Status;

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
    private Label status;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code JobCode} with the given {@code Job} and index to display.
     */
    public JobCard(Job job, int displayedIndex) {
        super(FXML);
        this.job = job;
        id.setText(displayedIndex + ". ");
        role.setText(job.getRole().description);
        company.setText(job.getCompany().name);
        status.setText(job.getStatus().status);
        setStatusLabelStyle();
        deadline.setText(job.getDeadline().deadline);
    }

    private void setStatusLabelStyle() {
        Status jobStatus = job.getStatus();

        if (jobStatus.equals(TO_BE_SUBMITTED)) {
            status.getStyleClass().add("to_be_submitted");
        } else if (jobStatus.equals(PENDING)) {
            status.getStyleClass().add("pending");
        } else if (jobStatus.equals(APPROVED)) {
            status.getStyleClass().add("approved");
        } else if (jobStatus.equals(REJECTED)) {
            status.getStyleClass().add("rejected");
        } else {
            status.getStyleClass().add("");
        }
    }
}
