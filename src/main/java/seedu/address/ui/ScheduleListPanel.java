package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * Panel containing the list of schedules.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static int dividerIndex = -1;
    private static final String FXML = "ScheduleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleListPanel.class);

    @FXML
    private ListView<Schedule> scheduleListView;

    /**
     * Creates a {@code ScheduleListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(ObservableList<Schedule> scheduleList) {
        super(FXML);
        scheduleListView.setItems(scheduleList);
        scheduleListView.setCellFactory(listView -> new ScheduleListViewCell());

        getDividerIndex(scheduleList);
        scheduleList.addListener((ListChangeListener<Schedule>) c -> getDividerIndex(scheduleList));
    }

    private void getDividerIndex(ObservableList<Schedule> scheduleList) {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < scheduleList.size(); i++) {
            if (scheduleList.get(i).getStartTime().compareDays(new StartTime(now)) < 0) {
                dividerIndex = i;
                break;
            }
        }

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Schedule} using a {@code ScheduleCard}.
     */
    class ScheduleListViewCell extends ListCell<Schedule> {
        @Override
        protected void updateItem(Schedule schedule, boolean empty) {
            super.updateItem(schedule, empty);

            if (empty || schedule == null) {
                setGraphic(null);
                setText(null);
            } else if (getIndex() == dividerIndex) {
                VBox container = new VBox();
                Label dividerLabel = new Label("Past Schedules");
                Separator separator = new Separator();
                container.getChildren()
                        .addAll(dividerLabel, separator, new ScheduleCard(schedule, getIndex() + 1).getRoot());
                setGraphic(container);
            } else {
                setGraphic(new ScheduleCard(schedule, getIndex() + 1).getRoot());
            }
        }
    }
}
