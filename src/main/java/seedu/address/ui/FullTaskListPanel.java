package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.lessons.Task;

/**
 * Panel containing the full list of tasks.
 */
public class FullTaskListPanel extends UiPart<Region> {
    private static final String FXML = "FullTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FullTaskListPanel.class);

    private Logic logic;
    private BooleanProperty reRenderUi;

    @FXML
    private ListView<Task> fullTaskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public FullTaskListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.reRenderUi = logic.getRefreshListUi(); // Connect to the logic manager's boolean flag
        fullTaskListView.setItems(logic.getFullTaskList());
        fullTaskListView.setCellFactory(listView -> new FullTaskListViewCell());
        // Listens for a change in reRenderUi value and updates UI, the actual value is irrelevant
        reRenderUi.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                refreshUI();
            }
        });

    }
    private void refreshUI() {
        fullTaskListView.setItems(logic.getFullTaskList());
        fullTaskListView.setCellFactory(listView -> new FullTaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class FullTaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FullTaskListCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
