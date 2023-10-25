package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.event.Event;

public class CalendarContainer extends UiPart<Region> {
    private static final String FXML = "CalendarContainer.fxml";

    private ObservableList<Event> currentWeekEventList;

    @FXML
    private StackPane calendarLabelPlaceholder;

    @FXML
    private GridPane eventSpace;

    public CalendarContainer(ObservableList<Event> currentWeekEventList) {
        super(FXML);
        this.currentWeekEventList = currentWeekEventList;

        fillCalendar();
    }

    public void fillCalendar() {
        calendarLabelPlaceholder.getChildren().add(new CalendarLabelColumn().getRoot());
    }
}
