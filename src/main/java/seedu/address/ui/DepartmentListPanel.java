package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.department.Department;

/**
 * Panel containing the list of employees.
 */
public class DepartmentListPanel extends UiPart<Region> {
    private static final String FXML = "DepartmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DepartmentListPanel.class);

    @FXML
    private ListView<Department> departmentListView;

    private DepartmentDetails departmentDetails;

    /**
     * Creates a {@code DepartmentListPanel} with the given {@code ObservableList}.
     */
    public DepartmentListPanel(ObservableList<Department> departmentList, DepartmentDetails departmentDetails) {
        super(FXML);
        this.departmentDetails = departmentDetails;
        departmentListView.setItems(departmentList);
        departmentListView.setCellFactory(listView -> new DepartmentListViewCell());

        MultipleSelectionModel<Department> selectionDepartmentList = departmentListView.getSelectionModel();

        selectionDepartmentList.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.departmentDetails.updateDetails(newValue);
        });

        departmentListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectionDepartmentList.clearSelection();
                Department nullDepartment = null;
                this.departmentDetails.updateDetails(nullDepartment);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Department} using a {@code DepartmentCard}.
     */
    class DepartmentListViewCell extends ListCell<Department> {
        @Override
        protected void updateItem(Department department, boolean empty) {
            super.updateItem(department, empty);

            if (empty || department == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DepartmentCard(department).getRoot());
            }
        }
    }

}
