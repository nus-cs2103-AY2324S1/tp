package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * Panel containing schedule of meetings.
 */
public class MeetingSchedulePanel extends UiPart<Region> {
    private static final String FXML = "MeetingSchedulePanel.fxml";

    @FXML
    private ListView<Meeting> meetingScheduleView;

    /**
     * Creates a {@code MeetingSchedulePanel} with the given {@code ObservableList}.
     */
    public MeetingSchedulePanel(ObservableList<Meeting> meetingList) {
        super(FXML);
        meetingScheduleView.setItems(meetingList);
        meetingScheduleView.setCellFactory(listView -> new MeetingScheduleViewCell());
        // Code to convert meetingList to a visual schedule
    }

    class MeetingScheduleViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }
}
