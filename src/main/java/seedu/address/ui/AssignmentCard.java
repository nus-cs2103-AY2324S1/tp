package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assignment.Assignment;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label endDate;
    @FXML
    private Label status;
    @FXML
    private Label plannedFinishDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(displayedIndex + ". ");
        name.setText(assignment.getName().toString());
        description.setText("Description: " + assignment.getDescription().toString());
        endDate.setText("Deadline: " + assignment.getEnd().toString());
        status.setText("Status: " + assignment.getStatus().toString());
        plannedFinishDate.setText("Plan to finish by: " + assignment.getPlannedFinishDate().toString());
        assignment.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    HBox container = new HBox();
                    Label l = new Label(tag.tagName);
                    container.getChildren().add(l);
                    l.setWrapText(true);
                    cardPane.widthProperty().addListener((obs, oldVal, newVal) -> {
                        l.setMaxWidth(newVal.doubleValue() - 25);
                    });
                    tags.getChildren().add(container);
                });
    }
}
