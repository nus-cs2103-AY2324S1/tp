package seedu.address.ui;

import java.awt.Font;
import java.time.DayOfWeek;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class DayCard extends UiPart<Region> {
    private static final String FXML = "DayCard.fxml";

    public final ObservableList<Calendar.GroupTimeContainer> dayTaskList;

    @FXML
    private HBox dayCard;
    @FXML
    private Label day;
    @FXML
    private Label task;

    @FXML
    private ListView<Calendar.GroupTimeContainer> eachDayTaskList;

    public DayCard(ObservableList<Calendar.GroupTimeContainer> dayTaskList, int dayIndex) {
        super(FXML);
        this.dayTaskList = dayTaskList;
        day.setText(DayOfWeek.of(dayIndex).toString().substring(0, 3));
        eachDayTaskList.setItems(dayTaskList);
        eachDayTaskList.setCellFactory(listview -> new eachDayTaskListCell());
    }

    class eachDayTaskListCell extends ListCell<Calendar.GroupTimeContainer> {
        @Override
        protected void updateItem(Calendar.GroupTimeContainer task, boolean empty) {
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
