package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.assignment.Assignment;

/**
 * Represents a UI component that displays an assignment in the user interface.
 */
public class AssignmentCard extends UiPart<Region> {
    private static final String FXML = "AssignmentListCard.fxml";

    /** The assignment associated with this card */
    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label gradeLabel;
    @FXML
    private Label commentTitle;
    @FXML
    private Label commentLabel;

    /**
     * Creates an `AssignmentCard` object to display the assignment.
     *
     * @param assignment The assignment being displayed.
     */
    public AssignmentCard(Assignment assignment) {
        super(FXML);
        this.assignment = assignment;
        nameLabel.setText(assignment.getName().toString());
        nameLabel.setStyle("-fx-font-size:18");
        gradeLabel.setText(assignment.getGrade().toString());
        gradeLabel.setStyle("-fx-font-size:12;");
        commentTitle.setText("Comment: ");
        commentTitle.setStyle("-fx-font-size:12;");
        commentLabel.setText(assignment.getComment().toString());
        commentLabel.setStyle("-fx-font-size:10;");
    }
}
