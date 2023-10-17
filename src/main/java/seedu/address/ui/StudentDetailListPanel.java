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
public class StudentDetailListPanel extends UiPart<Region> {
    private static final String FXML = "StudentDetailListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentDetailListPanel.class);

    private Logic logic;

    @FXML
    private ListView<Person> studentDetailListView;

    /**
     * Creates a {@code StudentDetailListPanel} with the given {@code ObservableList}.
     */
    public StudentDetailListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

}
