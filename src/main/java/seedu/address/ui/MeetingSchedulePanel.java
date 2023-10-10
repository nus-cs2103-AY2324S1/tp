package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel containing schedule of meetings.
 */
public class MeetingSchedulePanel extends UiPart<Region> {
    private static final String FXML = "MeetingSchedulePanel.fxml";

    @FXML
    private VBox meetingScheduleView;

    /**
     * Creates a {@code MeetingListPanel} with the given {@code ObservableList}.
     */
    public MeetingSchedulePanel() {
        super(FXML);
        // Code to convert meetingList to a visual schedule
    }

}
