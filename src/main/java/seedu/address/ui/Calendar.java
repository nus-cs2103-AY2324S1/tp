package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;
import seedu.address.ui.util.GroupTimeContainer;

/**
 * The UI component that is responsible for the calendar.
 */
public class Calendar extends UiPart<Region> {

    private static final String FXML = "Calendar.fxml";
    private final ObservableList<Group> groupList;
    private ObservableList<GroupTimeContainer> dayTaskMon = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskTue = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskWed = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskThu = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskFri = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskSat = FXCollections.observableArrayList();
    private ObservableList<GroupTimeContainer> dayTaskSun = FXCollections.observableArrayList();
    private ObservableList<ObservableList<GroupTimeContainer>> day = FXCollections.observableArrayList();

    @FXML
    private ListView<ObservableList<GroupTimeContainer>> dayListView;

    /**
     * Creates a {@code Calendar with tasks} with the given {@code groupList}.
     */
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
        groupList.addListener(new ListChangeListener<Group>() {
            @Override
            public void onChanged(Change<? extends Group> c) {
                clearDays();
                convertGrpListToContainer();
            }
        });
        convertGrpListToContainer();
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

    private void convertGrpListToContainer() {
        groupList.iterator().forEachRemaining(group -> {
            group.getTime().iterator().forEachRemaining(timeInterval -> {
                int startDay = timeInterval.getStartTimeDay().getValue() - 1;
                ObservableList<GroupTimeContainer> curDay = day.get(startDay);
                if (!curDay.contains(new GroupTimeContainer(group, timeInterval))) {
                    curDay.add(new GroupTimeContainer(group, timeInterval));
                }
            });
        });
    }

    private void clearDays() {
        dayTaskMon.clear();
        dayTaskTue.clear();
        dayTaskWed.clear();
        dayTaskThu.clear();
        dayTaskFri.clear();
        dayTaskSat.clear();
        dayTaskSun.clear();
    }

}
