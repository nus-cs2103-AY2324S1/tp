package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.assignment.AssignmentName;

/**
 * Represents a UI component that displays assignment names in the user interface.
 */
public class AssignmentNameListPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentNameListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentNameListPanel.class);

    @FXML
    private ListView<AssignmentName> assignmentNameListView;

    /**
     * Creates an `AssignmentNameListPanel` with the given `ObservableList` of assignment names.
     *
     * @param assignmentNameList The list of assignment names to display.
     */
    public AssignmentNameListPanel(ObservableList<AssignmentName> assignmentNameList) {
        super(FXML);
        assignmentNameListView.setItems(assignmentNameList);
        assignmentNameListView.setCellFactory(listView -> new AssignmentNameViewCell());
    }

    /**
     * Custom `ListCell` that displays the graphics of an `AssignmentName` using an `AssignmentNameCard`.
     */
    class AssignmentNameViewCell extends ListCell<AssignmentName> {
        @Override
        protected void updateItem(AssignmentName name, boolean empty) {
            super.updateItem(name, empty);

            if (empty || name == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentNameCard(name).getRoot());
            }
        }
    }
}
