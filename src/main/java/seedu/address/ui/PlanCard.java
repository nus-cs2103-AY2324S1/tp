package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.plan.Plan;

/**
 * A UI component that displays information of a {@code Plan}.
 */
public class PlanCard extends UiPart<Region> {

    private static final String FXML = "PlanListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Plan plan;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label completionStatus;
    @FXML
    private FlowPane friends;

    /**
     * Creates a {@code PlanCard} with the given {@code Plan} and index to display.
     */
    public PlanCard(Plan plan, int displayedIndex) {
        super(FXML);
        this.plan = plan;
        id.setText(displayedIndex + ". ");
        name.setText(plan.getPlanName().toString());
        dateTime.setText(plan.getPlanDateTime().toString());
        completionStatus.setText("Completed: " + plan.getCompletionString());
        friends.getChildren().add(new Label(plan.getPlanFriend().getName().toString()));
    }
}
