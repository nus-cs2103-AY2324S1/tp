package seedu.address.ui;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.schedule.Schedule;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CalendarRow extends UiPart<Region> {
    private static final String FXML = "CalendarRow.fxml";
    private static final LocalTime START_TIME = LocalTime.of(0, 0);
    private static final LocalTime END_TIME = LocalTime.of(23, 59);
    private static final int TOTAL_MINUTES = (int) (START_TIME.until(END_TIME, java.time.temporal.ChronoUnit.MINUTES));

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox rowLeftBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private HBox schedulesPane;

    private Color currentRowColor;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CalendarRow(CalendarPanel.PersonWithSchedules personWithSchedules, int displayedIndex) {
        super(FXML);
        displayedIndex--;

        rowLeftBox.getStyleClass().add("calendar-person-label");
        currentRowColor = calculateRowColor(displayedIndex);

        id.setText(displayedIndex + ". ");
        name.setText(personWithSchedules.getPerson().getName().fullName);

        List<Schedule> schedules = personWithSchedules.getSchedules();
        schedules.sort(Comparator.comparing(Schedule::getStartTime));

        double initialX = 0;
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            double startX = calculateXPosition(schedule.getStartTime().value.toLocalTime());
            double width = calculateWidth(schedule.getStartTime().value.toLocalTime(),
                schedule.getEndTime().value.toLocalTime());

            VBox card = new VBox();
            Color currentScheduleColor = calculateScheduleColor(i);
            card.setBackground(
                new Background(new BackgroundFill(currentScheduleColor, CornerRadii.EMPTY, Insets.EMPTY)));

            card.setAlignment(Pos.CENTER);
            card.setMinHeight(50);
            card.setMinWidth(width);
            card.setMaxWidth(width);
            card.setTranslateX(startX - initialX);

            initialX += width;

            Label scheduleLabel = new Label(String.format("%s - %s",
                schedule.getStartTime().toTimeString(), schedule.getEndTime().toTimeString()));
            scheduleLabel.getStyleClass().add("calendar-schedule-text");
            card.getChildren().add(scheduleLabel);
            schedulesPane.getChildren().add(card);
        }
    }

    /**
     * Creates a {@code PersonCode} with the given timeLabels to display.
     */
    public CalendarRow(List<Label> timeLabels) {
        super(FXML);

        rowLeftBox.getStyleClass().add("calendar-time-label");

        double initialX = 0;
        final int width = 50;
        for (int i = 0; i < timeLabels.size(); i++) {
            VBox card = new VBox();
            card.getStyleClass().add("calendar-time");
            card.setAlignment(Pos.CENTER);
            card.setMinHeight(50);
            card.setMinWidth(width);
            card.setMaxWidth(width);
            card.setTranslateX((i * width) - initialX);

            initialX += width;

            card.getChildren().add(timeLabels.get(i));
            schedulesPane.getChildren().add(card);
        }
    }

    private double calculateXPosition(LocalTime time) {
        int minutesFromStart = (int) (START_TIME.until(time, java.time.temporal.ChronoUnit.MINUTES));
        return rescale((double) minutesFromStart / TOTAL_MINUTES);
    }

    private double calculateWidth(LocalTime startTime, LocalTime endTime) {
        int minutesDuration = (int) (startTime.until(endTime, java.time.temporal.ChronoUnit.MINUTES));
        return rescale((double) minutesDuration / TOTAL_MINUTES);
    }

    private double rescale(double value) {
        return value * 2400;
    }

    private Color calculateRowColor(int index) {
        double hue = (index * 137.508) % 360;
        return Color.hsb(hue, 0.7, 1);
    }

    private Color calculateScheduleColor(int scheduleIndex) {
        double lightness = scheduleIndex % 2 == 0 ? 0.8 : 0.7;
        return currentRowColor.deriveColor(0, 1, lightness, 1);
    }
}
