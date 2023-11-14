package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.internship.task.InternshipTask;

/**
 * Panel to store InternshipTask
 */
public class InternshipTaskPanel extends UiPart<Region> {

    private static final String FXML = "InternshipTaskPanel.fxml";

    @FXML
    private ListView<InternshipTask> internshipTaskView;

    /**
     * Creates an InternshipTask panel with the given tasks
     * @param tasks
     */
    public InternshipTaskPanel(ObservableList<InternshipTask> tasks) {
        super(FXML);
        internshipTaskView.setItems(tasks);
        internshipTaskView.setCellFactory(listView -> new InternshipTaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of
     * a {@code InternshipTask} using a {@code InternshipTaskCard}.
     */
    class InternshipTaskListViewCell extends ListCell<InternshipTask> {
        @Override
        protected void updateItem(InternshipTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternshipTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }




}
