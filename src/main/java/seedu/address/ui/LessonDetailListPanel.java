package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Panel containing a student's details.
 */
public class LessonDetailListPanel extends UiPart<Region> {
    private static final String FXML = "LessonDetailListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonDetailListPanel.class);

    private Logic logic;

    @FXML
    private ListView<Person> lessonDetailListView;

    /**
     * Creates a {@code LessonDetailListPanel} with the given {@code ObservableList}.
     */
    public LessonDetailListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

}
