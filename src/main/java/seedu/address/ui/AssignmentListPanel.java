package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.assignment.Assignment;

/**
 * Represents a UI component that displays assignments in the user interface.
 */
public class AssignmentListPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentListPanel.class);

    @FXML
    private ListView<Assignment> assignmentListView;

    /**
     * Creates an `AssignmentListPanel` with the given `ObservableList` of assignments.
     *
     * @param assignmentList The list of assignments to display.
     */
    public AssignmentListPanel(ObservableList<Assignment> assignmentList) {
        super(FXML);
        assignmentListView.setItems(assignmentList);
        assignmentListView.setCellFactory(listView -> new AssignmentViewCell());
    }

    /**
     * Custom `ListCell` that displays the graphics of an `Assignment` using an `AssignmentCard`.
     */
    class AssignmentViewCell extends ListCell<Assignment> {
        @Override
        protected void updateItem(Assignment assignment, boolean empty) {
            super.updateItem(assignment, empty);

            if (empty || assignment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentCard(assignment).getRoot());
            }
        }
    }
}
