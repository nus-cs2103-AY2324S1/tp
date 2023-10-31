package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.lessons.Task;

/**
 * Panel containing a student's details.
 */
public class TaskDetailPanel extends UiPart<Region> {
    private static final String FXML = "TaskDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskDetailPanel.class);

    private Logic logic;

    @FXML
    private Label desc;
    @FXML
    private Label isDone;


    /**
     * Creates a {@code TaskDetailPanel} with the given {@code ObservableList}.
     */
    public TaskDetailPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }


    /**
     * Sets the Details of the task to be shown.
     *
     * @param task The task whose details are to be shown.
     */
    public void setTaskDetails(Task task) {
        desc.setText(task.getDescription());
        if (task.isDone()) {
            isDone.setText("✅");
        } else {
            isDone.setText("❌");
        }
    }

}
