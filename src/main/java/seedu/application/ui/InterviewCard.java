package seedu.application.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.application.model.job.interview.Interview;

/**
 * A UI component that shows information regarding an {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    public static final String FXML = "InterviewListCard.fxml";

    public final Interview interview;

    @FXML
    private HBox cardPane;

    @FXML
    private Label title;

    @FXML
    private Label dateTime;

    @FXML
    private Label address;

    /**
     * Creates a {@code InterviewCard} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int index) {
        super(FXML);
        this.interview = interview;
        String type = interview.getInterviewType().toString();
        this.title.setText(index + ". " + type + " Interview");
        dateTime.setText(interview.getInterviewDateTime().toString());
        address.setText(interview.getInterviewAddress().toString());
    }
}
