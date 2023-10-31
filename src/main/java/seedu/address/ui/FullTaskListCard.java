package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.lessons.Task;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class FullTaskListCard extends UiPart<Region> {

    private static final String FXML = "FullTaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;
    @FXML
    private VBox fields;

    @FXML
    private HBox cardPane;
    @FXML
    private Label desc;
    @FXML
    private Label id;


    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public FullTaskListCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        desc.setText(task.getDescription());
    }
}
