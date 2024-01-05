package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.assignment.AssignmentName;

/**
 * Represents a UI component that displays an assignment name in the user interface.
 */
public class AssignmentNameCard extends UiPart<Region> {
    private static final String FXML = "AssignmentNameListCard.fxml";

    /** The assignment name associated with this card */
    public final AssignmentName name;

    @FXML
    private HBox cardPane;
    @FXML
    private Label assignmentNameLabel;

    /**
     * Creates an `AssignmentNameCard` object to display the assignment name.
     *
     * @param name The name of an assignment.
     */
    public AssignmentNameCard(AssignmentName name) {
        super(FXML);
        this.name = name;
        assignmentNameLabel.setText(name.toString());
    }
}
