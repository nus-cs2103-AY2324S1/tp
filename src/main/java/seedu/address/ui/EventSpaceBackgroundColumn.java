package seedu.address.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * The UI component that represents a column in the event space background, displaying time intervals.
 */
public class EventSpaceBackgroundColumn extends UiPart<Region> {
    private static final String FXML = "EventSpaceBackgroundColumn.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalTime startTime;

    @FXML
    private Label startTimeLabel;

    /**
     * Constructs an EventSpaceBackgroundColumn with the specified start time.
     *
     * @param startTime The start time represented by this column.
     */
    public EventSpaceBackgroundColumn(LocalTime startTime) {
        super(FXML);
        this.startTime = startTime;

        setLabel();
    }

    /**
     * Checks if this column's time interval is within the specified time range.
     *
     * @param start The start time of the range (inclusive).
     * @param end The end time of the range (exclusive).
     * @return True if the column's time interval is within the specified range; otherwise, false.
     */
    public boolean isWithin(LocalTime start, LocalTime end) {
        return (startTime.equals(start) || (startTime.isAfter(start) && startTime.isBefore(end)))
                && !startTime.equals(end);
    }

    /**
     * Sets the label to display the start time formatted as "HH:mm".
     */
    private void setLabel() {
        startTimeLabel.setText(startTime.format(TIME_FORMATTER));
    }
}
