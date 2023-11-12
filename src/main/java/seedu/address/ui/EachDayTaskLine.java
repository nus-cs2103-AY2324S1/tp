package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.util.GroupTimeContainer;

/**
 * The UI component that is responsible for each line in each day task list
 */
public class EachDayTaskLine extends UiPart<Region> {

    private static final String FXML = "EachDayTaskLine.fxml";
    private final GroupTimeContainer task;
    @FXML
    private HBox individualTaskLine;
    @FXML
    private Label dot;
    @FXML
    private Label groupInSch;
    @FXML
    private Label taskLine;

    /**
     * Creates a {@code line of group name and meeting time} with the given {@code task}.
     */
    public EachDayTaskLine(GroupTimeContainer task) {
        super(FXML);
        this.task = task;
        groupInSch.setText(task.getGroup().getGroupName());
        taskLine.setText(task.getTimeInterval().toString());
    }

}
