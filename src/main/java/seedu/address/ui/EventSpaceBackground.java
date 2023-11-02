package seedu.address.ui;

import java.time.LocalTime;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;

/**
 * The UI component that represents the background grid for an event space, displaying time intervals.
 */
public class EventSpaceBackground extends UiPart<Region> {
    private static final String FXML = "EventSpaceBackground.fxml";
    private static final LocalTime DEFAULT_CALENDAR_START_TIME = LocalTime.of(8, 0);
    private static final LocalTime DEFAULT_CALENDAR_END_TIME = LocalTime.of(18, 0);
    private static final int NUMBER_OF_HOURS_IN_ONE_DAY = 24;
    private static final int MAXIMUM_DISPLAY_HOUR = 23;
    private static final int NUMBER_OF_MINUTES_IN_AN_HOUR = 60;

    private LocalTime calendarStartTime = DEFAULT_CALENDAR_START_TIME;
    private LocalTime calendarEndTime = DEFAULT_CALENDAR_END_TIME;

    private ReadOnlyCalendar calendar;
    private ObservableList<Event> eventList;
    private ObservableList<EventSpaceBackgroundColumn> columns;

    @FXML
    private HBox backgroundGrid;

    /**
     * Constructs an EventSpaceBackground with the given ReadOnlyCalendar.
     *
     * @param calendar The ReadOnlyCalendar to be displayed in the background.
     */
    public EventSpaceBackground(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;
        eventList = calendar.getCurrentWeekEventList();
        columns = FXCollections.<EventSpaceBackgroundColumn>observableArrayList();

        fillBackground();
        updateStartAndEnd();
        showRelevantBackground();
        addListenerToEventList();
    }


    /**
     * Adds a listener to the event list to update the background when events change.
     */
    private void addListenerToEventList() {
        eventList.addListener((ListChangeListener<Event>) c -> {
            updateStartAndEnd();
            showRelevantBackground();
        });
    }

    /**
     * Fills the background with time intervals.
     */
    public void fillBackground() {
        Stream.<LocalTime>iterate(LocalTime.MIDNIGHT, time -> time.plusHours(1))
                .limit(NUMBER_OF_HOURS_IN_ONE_DAY)
                .map(EventSpaceBackgroundColumn::new)
                .forEachOrdered(column -> columns.add(column));
        columns.forEach(column -> backgroundGrid.getChildren().add(column.getRoot()));
    }

    /**
     * Updates the start and end times based on the events in the calendar.
     */
    private void updateStartAndEnd() {
        LocalTime newStartTime = calendar.getEarliestEventStartTimeInCurrentWeek()
                .map(time -> {
                    return time.minusMinutes(time.getMinute());
                })
                .orElse(DEFAULT_CALENDAR_START_TIME);
        LocalTime newEndTime = calendar.getLatestEventEndTimeInCurrentWeek()
                .map(time -> {
                    if (time.getMinute() == 0 || time.getHour() == MAXIMUM_DISPLAY_HOUR) {
                        return time;
                    }
                    return time.plusMinutes(NUMBER_OF_MINUTES_IN_AN_HOUR - time.getMinute());
                })
                .orElse(DEFAULT_CALENDAR_END_TIME);

        calendarStartTime = newStartTime.isBefore(DEFAULT_CALENDAR_START_TIME)
                ? newStartTime : DEFAULT_CALENDAR_START_TIME;
        calendarEndTime = newEndTime.isAfter(DEFAULT_CALENDAR_END_TIME)
                ? newEndTime : DEFAULT_CALENDAR_END_TIME;
    }

    /**
     * Shows the relevant background columns based on the current time range.
     */
    private void showRelevantBackground() {
        columns.stream().filter(column -> !column.isWithin(calendarStartTime, calendarEndTime))
                .map(EventSpaceBackgroundColumn::getRoot).forEach(region -> {
                    region.setVisible(false);
                    region.setManaged(false);
                });
        columns.stream().filter(column -> column.isWithin(calendarStartTime, calendarEndTime))
                .map(EventSpaceBackgroundColumn::getRoot).forEach(region -> {
                    region.setVisible(true);
                    region.setManaged(true);
                });
    }
}
