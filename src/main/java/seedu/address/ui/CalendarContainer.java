package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.calendar.ReadOnlyCalendar;

public class CalendarContainer extends UiPart<Region> {
    private static final String FXML = "CalendarContainer.fxml";

    private ReadOnlyCalendar calendar;

    @FXML
    private StackPane calendarLabelPlaceholder;

    @FXML
    private GridPane eventSpace;

    public CalendarContainer(ReadOnlyCalendar calendar) {
        super(FXML);
        this.calendar = calendar;

        fillCalendar();
    }

    public void fillCalendar() {
        calendarLabelPlaceholder.getChildren().add(new CalendarLabelColumn().getRoot());
        eventSpace.getChildren().add(new EventSpaceBackground(calendar).getRoot());
    }
}
