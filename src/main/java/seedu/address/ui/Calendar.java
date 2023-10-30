package seedu.address.ui;

import java.net.URL;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class Calendar extends UiPart<Region> {

    private static final String FXML = "CalendarList.fxml";
    private final ObservableList<Group> groupList;
    private ObservableList<GroupTimeContainer> dayTaskMon = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskTue = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskWed = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskThu = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskFri = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskSat = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskSun = FXCollections.observableArrayList();
    private ObservableList<ObservableList<GroupTimeContainer>> day = FXCollections.observableArrayList();


    private void convert() {
        groupList.iterator().forEachRemaining(group -> {
            group.getTime().iterator().forEachRemaining(timeInterval -> {
                int startDay = timeInterval.getStartTimeDay().getValue() - 1;
                ObservableList<GroupTimeContainer> curDay = day.get(startDay);
                curDay.add(new GroupTimeContainer(group, timeInterval));
            });
        });
    }




    @FXML
    private ListView<ObservableList<GroupTimeContainer>> dayListView;

    public Calendar(ObservableList<Group> groupList) {
        super(FXML);
        this.groupList = groupList;
        day.add(dayTaskMon); //mon
        day.add(dayTaskTue); //tue
        day.add(dayTaskWed); //wed
        day.add(dayTaskThu); //thu
        day.add(dayTaskFri); //fri
        day.add(dayTaskSat); //sat
        day.add(dayTaskSun); //sun
        convert();
        dayListView.setItems(day);
        dayListView.setCellFactory(listView -> new DayListViewCell());
    }

    class DayListViewCell extends ListCell<ObservableList<GroupTimeContainer>> {
        @Override
        protected void updateItem(ObservableList<GroupTimeContainer> grpTimeContainer, boolean empty) {
            super.updateItem(grpTimeContainer, empty);
            if (empty || grpTimeContainer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(grpTimeContainer, getIndex() + 1).getRoot());
            }
        }
    }

    class GroupTimeContainer {
        private final Group group;

        private final TimeInterval timeInterval;

        public GroupTimeContainer(Group group, TimeInterval timeInterval) {
            this.group = group;
            this.timeInterval = timeInterval;
        }

        public Group getGroup() {
            return group;
        }

        public TimeInterval getTimeInterval() {
            return timeInterval;
        }
    }
}
