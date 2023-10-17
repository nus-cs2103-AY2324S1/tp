package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Panel containing the schedule.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScheduleListPanel.class);

    private Logic logic;

    @FXML
    private ListView<Person> scheduleListView;

    /**
     * Creates a {@code ScheduleListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

}
