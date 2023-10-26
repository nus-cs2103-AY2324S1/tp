package seedu.address.ui;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.calendar.ReadOnlyCalendar;

public class EventSpaceBackground extends UiPart<Region> {
    private static final String FXML = "EventSpaceBackground.fxml";
    private static final LocalTime DEFAULT_CALENDAR_START_TIME = LocalTime.of(8, 0);
    private static final LocalTime DEFAULT_CALENDAR_END_TIME = LocalTime.of(18, 0);
    private static final int NUMBER_OF_HOURS_IN_ONE_DAY = 24;

    private LocalTime calendarStartTime = DEFAULT_CALENDAR_START_TIME;
    private LocalTime calendarEndTime = DEFAULT_CALENDAR_END_TIME;

    private ReadOnlyCalendar calendar;
    private ObservableList<EventSpaceBackgroundColumn> columns;

    @FXML
    private HBox backgroundGrid;

    @FXML
    private GridPane eventGrid;

    public EventSpaceBackground(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;
        this.columns = FXCollections.<EventSpaceBackgroundColumn>observableArrayList();

        fillBackground();
        updateStartAndEnd();
        showRelevantBackground();
    }

    public void fillBackground() {
        Stream.<LocalTime>iterate(LocalTime.MIDNIGHT, time -> time.plusHours(1))
                .limit(NUMBER_OF_HOURS_IN_ONE_DAY)
                .map(EventSpaceBackgroundColumn::new)
                .forEachOrdered(column -> columns.add(column));
        columns.forEach(column -> backgroundGrid.getChildren().add(column.getRoot()));
    }

    public void updateStartAndEnd() {
        calendarStartTime = calendar.getEarliestEventStartTimeInCurrentWeek().orElse(calendarStartTime);
        calendarEndTime = calendar.getLatestEventEndTimeInCurrentWeek().orElse(calendarEndTime);
    }

    public void showRelevantBackground() {
        columns.stream().filter(column -> !column.isWithin(calendarStartTime, calendarEndTime))
                .map(EventSpaceBackgroundColumn::getRoot).forEach(region -> {
                    region.setVisible(false);
                    region.setManaged(false);
                });
    }
}
