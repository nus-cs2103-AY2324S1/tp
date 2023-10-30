package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class EachDayTaskLine extends UiPart<Region> {

    private static final String FXML = "GroupTaskCell.fxml";
    private final GroupTimeContainer task;
    @FXML
    private HBox individualTaskLine;
    @FXML
    private Label dot;
    @FXML
    private Label group_in_sch;
    @FXML
    private Label taskLine;

    public EachDayTaskLine(GroupTimeContainer task) {
        super(FXML);
        this.task = task;
        group_in_sch.setText(task.getGroup().getGroupName());
        taskLine.setText(task.getTimeInterval().toString());
    }

}
