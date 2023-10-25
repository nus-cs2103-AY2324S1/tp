package seedu.address.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class EventSpaceBackgroundColumn extends UiPart<Region> {
    private static final String FXML = "EventSpaceBackgroundColumn.fxml";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hha");

    private final LocalTime startTime;

    @FXML
    private Label startTimeLabel;

    public EventSpaceBackgroundColumn(LocalTime startTime) {
        super(FXML);
        this.startTime = startTime;

        setLabel();
    }

    public boolean isWithin(LocalTime start, LocalTime end) {
        return (startTime.equals(start) || startTime.equals(end))
                || (startTime.isAfter(start) && startTime.isBefore(end));
    }

    public void setLabel() {
        startTimeLabel.setText(startTime.format(TIME_FORMATTER));
    }
}
