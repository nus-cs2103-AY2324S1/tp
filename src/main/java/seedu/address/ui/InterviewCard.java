package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;

/**
 * An UI component that displays information of a {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Interview interview;

    @FXML
    private HBox cardPane;
    @FXML
    private Label applicant;
    @FXML
    private Label id;
    @FXML
    private Label jobRole;
    @FXML
    private Label interviewStartTime;
    @FXML
    private Label interviewEndTime;
    @FXML
    private Label rating;

    /**
     * Creates a {@code InterviewCard} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        id.setText(displayedIndex + ". ");
        applicant.setText(interview.getInterviewApplicant().getName().fullName);
        jobRole.setText(interview.getJobRole());
        interviewStartTime.setText(interview.getInterviewStartTimeAsString());
        interviewEndTime.setText(interview.getInterviewEndTimeAsString());
        rating.setText(interview.getRating().rating);
        if (interview.isDone()) {
            cardPane.setStyle("-fx-border-color: #2c7a2c; -fx-border-width: 3;");
        } else {
            cardPane.setStyle("-fx-border-color: #7a2c2c; -fx-border-width: 3;");
        }
    }
}
