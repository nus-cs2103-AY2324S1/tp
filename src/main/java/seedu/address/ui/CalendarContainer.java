package seedu.address.ui;

import static seedu.address.ui.UiConstants.POPUP_CALENDAR_HEIGHT;
import static seedu.address.ui.UiConstants.POPUP_CALENDAR_WIDTH;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.model.calendar.ReadOnlyCalendar;

/**
 * Represents the container for displaying the calendar view in the user interface.
 * It contains various graphical elements such as the calendar label, event space, and background.
 */
public class CalendarContainer extends UiPart<Region> {
    private static final String FXML = "CalendarContainer.fxml";

    private ReadOnlyCalendar calendar;
    @FXML
    private StackPane calendarLabelPlaceholder;

    @FXML
    private StackPane eventSpaceContainer;

    @FXML
    private GridPane eventSpaceBackground;
    @FXML
    private GridPane eventSpace;

    /**
     * Constructs a CalendarContainer with the specified ReadOnlyCalendar.
     *
     * @param calendar The ReadOnlyCalendar to be displayed in the container.
     */
    public CalendarContainer(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;
    }

    public static CalendarContainer createDefaultCalendar(ReadOnlyCalendar calendar) {
        CalendarContainer defaultCalendarContainer = new CalendarContainer(calendar);
        defaultCalendarContainer.fillCalendar();
        return defaultCalendarContainer;
    }

    private static CalendarContainer createComparisonCalendar(ReadOnlyCalendar calendar) {
        CalendarContainer comparisonCalendarContainer = new CalendarContainer(calendar);
        comparisonCalendarContainer.fillComparisonCalendar();
        return comparisonCalendarContainer;
    }

    public static void displayComparisonCalendar(ReadOnlyCalendar calendar) {
        Stage comparisonCalendarStage = new Stage();
        comparisonCalendarStage.setResizable(false);
        comparisonCalendarStage.setMinHeight(POPUP_CALENDAR_HEIGHT);
        comparisonCalendarStage.setMinWidth(POPUP_CALENDAR_WIDTH);
        CalendarContainer root = CalendarContainer.createComparisonCalendar(calendar);
        comparisonCalendarStage.setScene(new Scene(root.getRoot()));
        comparisonCalendarStage.show();
    }

    /**
     * Fills the calendar container with the necessary components (calendar labels and event spaces).
     */
    public void fillCalendar() {
        calendarLabelPlaceholder.getChildren().add(new CalendarLabelColumn().getRoot());
        eventSpaceBackground.getChildren().add(new EventSpaceBackground(calendar).getRoot());
        eventSpace.getChildren().add(CalendarEventSpace.createDefaultEventSpace(calendar).getRoot());
    }

    public void fillComparisonCalendar() {
        calendarLabelPlaceholder.getChildren().add(new CalendarLabelColumn().getRoot());
        eventSpaceBackground.getChildren().add(new EventSpaceBackground(calendar).getRoot());
        eventSpace.getChildren().add(CalendarEventSpace.createComparisonCalendarEventSpace(calendar).getRoot());
    }
}
