package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.employee.Employee;

/**
 * Panel containing the list of employees.
 */
public class EmployeeListPanel extends UiPart<Region> {
    private static final String FXML = "EmployeeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EmployeeListPanel.class);

    @FXML
    private ListView<Employee> employeeListView;

    private ProfileDetails profileDetails;

    /**
     * Creates a {@code EmployeeListPanel} with the given {@code ObservableList}.
     */
    public EmployeeListPanel(ObservableList<Employee> employeeList, ProfileDetails profileDetails) {
        super(FXML);
        this.profileDetails = profileDetails;
        employeeListView.setItems(employeeList);
        employeeListView.setCellFactory(listView -> new EmployeeListViewCell());

        MultipleSelectionModel<Employee> selectionEmployeeList = employeeListView.getSelectionModel();

        selectionEmployeeList.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.profileDetails.updateDetails(newValue);
        });

        employeeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectionEmployeeList.clearSelection();
                this.profileDetails.updateDetails(null);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Employee} using a {@code EmployeeCard}.
     */
    class EmployeeListViewCell extends ListCell<Employee> {
        @Override
        protected void updateItem(Employee employee, boolean empty) {
            super.updateItem(employee, empty);

            if (empty || employee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EmployeeCard(employee, getIndex() + 1).getRoot());
            }
        }
    }

}
