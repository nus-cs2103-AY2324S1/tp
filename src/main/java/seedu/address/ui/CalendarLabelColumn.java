package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * The UI component that represents the column of labels in a calendar view, typically displaying day names or headers.
 */
public class CalendarLabelColumn extends UiPart<Region> {
    private static final String FXML = "CalendarLabelColumn.fxml";

    /**
     * Constructs a CalendarLabelColumn with the appropriate FXML file.
     */
    public CalendarLabelColumn() {
        super(FXML);
    }
}
