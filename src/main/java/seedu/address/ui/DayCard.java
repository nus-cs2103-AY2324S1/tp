package seedu.address.ui;

import java.time.DayOfWeek;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.util.GroupTimeContainer;

/**
 * The UI component that is responsible for each day in the calendar
 */
public class DayCard extends UiPart<Region> {
    private static final String FXML = "DayCard.fxml";

    public final ObservableList<GroupTimeContainer> dayTaskList;
    @FXML
    private HBox dayCard;
    @FXML
    private Label day;
    @FXML
    private Label task;
    @FXML
    private ListView<GroupTimeContainer> eachDayTaskList;

    /**
     * Creates a {@code DayCard in the calendar} with the given {@code dayTaskList} and index to display day.
     */
    public DayCard(ObservableList<GroupTimeContainer> dayTaskList, int dayIndex) {
        super(FXML);
        this.dayTaskList = dayTaskList;
        day.setText(DayOfWeek.of(dayIndex).toString().substring(0, 3));
        eachDayTaskList.setItems(dayTaskList);
        eachDayTaskList.setCellFactory(listview -> new EachDayTaskListCell());
    }

    class EachDayTaskListCell extends ListCell<GroupTimeContainer> {
        @Override
        protected void updateItem(GroupTimeContainer task, boolean empty) {
            super.updateItem(task, empty);
            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EachDayTaskLine(task).getRoot());
            }
        }
    }



}
