package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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

        fillCalendar();
    }

    /**
     * Fills the calendar container with the necessary components (calendar labels and event spaces).
     */
    public void fillCalendar() {
        calendarLabelPlaceholder.getChildren().add(new CalendarLabelColumn().getRoot());
        eventSpaceBackground.getChildren().add(new EventSpaceBackground(calendar).getRoot());
        eventSpace.getChildren().add(new CalendarEventSpace(calendar).getRoot());
    }

    public void setComparisonStyle() {

    }
}
