package seedu.address.ui;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.event.Event;

public class CalendarEventSpace extends UiPart<Region> {
    private static final String FXML = "CalendarEventSpace.fxml";
    private static final LocalTime DEFAULT_CALENDAR_START_TIME = LocalTime.of(8, 0);
    private static final LocalTime DEFAULT_CALENDAR_END_TIME = LocalTime.of(18, 0);
    private static final int NUMBER_OF_ROWS = 8;
    private static final int NUMBER_OF_HALF_HOURS_IN_HOUR= 2;
    private static final int NUMBER_OF_MINUTES_IN_HALF_HOUR = 30;
    private static final int NUMBER_OF_MINUTES_IN_AN_HOUR = 60;
    private static final int NODE_WIDTH_PER_HALF_HOUR = 25;
    private static final int MAXIMUM_DISPLAY_HOUR_OF_DAY = 23;
    private static final int NODE_HEIGHT = 30;


    private final ReadOnlyCalendar calendar;
    private final ObservableList<Event> eventList;
    private final ObservableList<AnchorPane> rowList = FXCollections.<AnchorPane>observableArrayList();
    private LocalTime calendarStartTime = DEFAULT_CALENDAR_START_TIME;
    private LocalTime calendarEndTime = DEFAULT_CALENDAR_END_TIME;

    @FXML
    private GridPane eventSpace;

    public CalendarEventSpace(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;
        this.eventList = calendar.getEventList();

        updateStartAndEnd();
        initializeEventSpace();
        addEventCards();
    }

    private void initializeEventSpace() {
        Stream.<AnchorPane>generate(AnchorPane::new).limit(NUMBER_OF_ROWS).forEachOrdered(pane -> {
            pane.setPrefHeight(NODE_HEIGHT);
            pane.setPrefWidth(calculateCalendarWidth());
            eventSpace.addRow(eventSpace.getRowCount(), pane);
            rowList.add(pane);
                });
    }

    private int calculateCalendarWidth() {
        long minutesBetweenStartAndEnd = MINUTES.between(calendarStartTime, calendarEndTime);
        int numberOfHoursToShow = calendarEndTime.getHour() - calendarStartTime.getHour();
        if (minutesBetweenStartAndEnd % NUMBER_OF_MINUTES_IN_AN_HOUR > 0) {
            numberOfHoursToShow++;
        }
        return numberOfHoursToShow * NODE_WIDTH_PER_HALF_HOUR * NUMBER_OF_HALF_HOURS_IN_HOUR;
    }

    public void updateStartAndEnd() {
        LocalTime newStartTime = calendar.getEarliestEventStartTimeInCurrentWeek()
                .map(time -> {
                    return time.minusMinutes(time.getMinute());
                })
                .orElse(calendarStartTime);
        LocalTime newEndTime = calendar.getLatestEventEndTimeInCurrentWeek()
                .map(time -> {
                    if (time.getMinute() == 0) {
                        return time;
                    }

                    if (time.getHour() == MAXIMUM_DISPLAY_HOUR_OF_DAY) {
                        return time;
                    }

                    return LocalTime.of(time.getHour() + 1, 0);
                })
                .orElse(calendarEndTime);

        calendarStartTime = newStartTime.isBefore(DEFAULT_CALENDAR_START_TIME) ?
                newStartTime : DEFAULT_CALENDAR_START_TIME;
        calendarEndTime = newEndTime.isAfter(DEFAULT_CALENDAR_END_TIME) ?
                newEndTime : DEFAULT_CALENDAR_END_TIME;
    }

    public void addEventCards() {
        eventList.forEach(event -> {
            StackPane eventNode = generateEventCard(event);
            rowList.get(findRow(event)).getChildren().add(eventNode);
            AnchorPane.setLeftAnchor(eventNode, findLeftOffset(event));
        });
    }

    public StackPane generateEventCard(Event event) {
        double widthMultiplier = event.getDurationOfEvent().getHour()
                + ((double) (event.getDurationOfEvent().getMinute()) / NUMBER_OF_MINUTES_IN_AN_HOUR);
        StackPane cardHolder = new StackPane();

        Rectangle cardRectangle = new Rectangle();
        cardRectangle.setHeight(NODE_HEIGHT);
        cardRectangle.setWidth(widthMultiplier * NODE_WIDTH_PER_HALF_HOUR * NUMBER_OF_HALF_HOURS_IN_HOUR);
        cardRectangle.setFill(Color.CRIMSON);


        Label description = new Label();
        description.setMaxHeight(NODE_HEIGHT);
        description.setMaxWidth(widthMultiplier * NODE_WIDTH_PER_HALF_HOUR * NUMBER_OF_HALF_HOURS_IN_HOUR);
        description.setText(event.getDescriptionString());

        cardHolder.getChildren().addAll(cardRectangle, description);
        return cardHolder;
    }

    private int findRow(Event event) {
        return event.getDayOfWeek().getValue();
    }

    private double findLeftOffset(Event event) {
        return ((double) event.getMinutesFromTimeToStartTime(calendarStartTime)
                / NUMBER_OF_MINUTES_IN_HALF_HOUR) * NODE_WIDTH_PER_HALF_HOUR;
    }

}
