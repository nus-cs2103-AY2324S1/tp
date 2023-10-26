package seedu.address.ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import seedu.address.model.calendar.ReadOnlyCalendar;

public class CalendarEventSpace extends UiPart<Region> {
    private static final String FXML = "CalendarEventSpace.fxml";
    private static final LocalTime DEFAULT_CALENDAR_START_TIME = LocalTime.of(8, 0);
    private static final LocalTime DEFAULT_CALENDAR_END_TIME = LocalTime.of(18, 0);
    private static final int NUMBER_OF_ROWS = 8;
    private static final int NUMBER_OF_COLUMNS = 24;
    private static final int NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN = 2;
    private static final int NUMBER_OF_MINUTES_IN_HALF_HOUR = 30;
    private static final int NODE_WIDTH_PER_HALF_HOUR = 25;
    private static final int NODE_HEIGHT = 30;


    private final ReadOnlyCalendar calendar;
    private LocalTime calendarStartTime = DEFAULT_CALENDAR_START_TIME;
    private LocalTime calendarEndTime = DEFAULT_CALENDAR_END_TIME;
    private final Rectangle[][] placeholderGrid =
            new Rectangle[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS*NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN];

    @FXML
    private GridPane eventSpace;

    public CalendarEventSpace(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;

        initializeEventSpace();
    }

    public void initializeEventSpace() {

        int numberOfHalfHoursPerDay = NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN * NUMBER_OF_COLUMNS;
        Stream.<Integer>iterate(0, x -> x + 1)
                .limit(NUMBER_OF_ROWS * numberOfHalfHoursPerDay)
                .forEach(x -> {
                    int row = x / numberOfHalfHoursPerDay;
                    int col = x % numberOfHalfHoursPerDay;
                    Rectangle placeholder = createPlaceholder();
                    eventSpace.add(placeholder, col, row);
                    placeholderGrid[row][col] = placeholder;
                        });
    }

    private Rectangle createPlaceholder() {
        Rectangle invisiblePlaceholder = new Rectangle();
        invisiblePlaceholder.setHeight(NODE_HEIGHT);
        invisiblePlaceholder.setWidth(NODE_WIDTH_PER_HALF_HOUR);
        invisiblePlaceholder.setOpacity(0);
        return invisiblePlaceholder;
    }

    public void updateStartAndEnd() {
        calendarStartTime = calendar.getEarliestEventStartTimeInCurrentWeek().orElse(calendarStartTime);
        calendarEndTime = calendar.getLatestEventEndTimeInCurrentWeek().orElse(calendarEndTime);
    }

    public void showRelevantPlaceholders() {
        long columnsToExcludeOnLeft = Stream.<LocalTime>iterate(
                LocalTime.MIDNIGHT, time -> time.plusMinutes(NUMBER_OF_MINUTES_IN_HALF_HOUR))
                .limit(NUMBER_OF_COLUMNS * NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN)
                .filter(time -> time.isBefore(calendarStartTime)).count();
        long columnsToExcludeOnRight = Stream.<LocalTime>iterate(
                        LocalTime.MIDNIGHT, time -> time.plusMinutes(NUMBER_OF_MINUTES_IN_HALF_HOUR))
                .limit(NUMBER_OF_COLUMNS * NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN)
                .filter(time -> time.isAfter(calendarEndTime)).count();

        Stream.<Integer>iterate(0, x -> x + 1).limit(columnsToExcludeOnLeft).forEach(column -> {
            Stream.<Integer>iterate(0, x -> x + 1).limit(NUMBER_OF_ROWS).forEach(row -> {
                        placeholderGrid[row][column].setVisible(false);
                        placeholderGrid[row][column].setManaged(false);
                    });
        });

        Stream.<Integer>iterate(0, x -> x + 1).limit(columnsToExcludeOnRight).forEach(column -> {
            Stream.<Integer>iterate(0, x -> x + 1).limit(NUMBER_OF_ROWS).forEach(row -> {
                placeholderGrid[row][(NUMBER_OF_COLUMNS * NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN) - column]
                        .setVisible(false);
                placeholderGrid[row][(NUMBER_OF_COLUMNS * NUMBER_OF_HALF_HOURS_IN_SINGLE_COLUMN) - column]
                        .setManaged(false);
            });
        });
    }
}
