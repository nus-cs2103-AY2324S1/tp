package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.department.Department;

/**
 * A UI component for displaying detailed information about an employee's profile. This component
 * displays the employee's name, phone number, address, email, salary, and the departments they belong to.
 */
public class DepartmentDetails extends UiPart<Region> {
    private static final String FXML = "DepartmentDetails.fxml";

    private Department department;
    @FXML
    private Label name;
    @FXML
    private FlowPane listView;

    /**
     * Creates a {@code EmployeeCode} with the given {@code Employee} and index to display.
     */
    public DepartmentDetails() {
        super(FXML);
        //this.getRoot().setVisible(false);
    }

    /**
     * Updates the details displayed in the profile component based on the provided {@code Department} object.
     * If the department is null, the component is hidden. Otherwise, it displays the department's information,
     * including name, employees
     *
     * @param department The {@code Department} object for which to display the details.
     */
    public void updateDetails(Department department) {
        if (department == null) {
            this.getRoot().setVisible(false);
        } else {
            name.setText(department.getName());
            listView.getChildren().clear();
            department.getEmployees().stream()
                    .sorted(Comparator.comparing(employee -> employee.fullName))
                    .forEach(employee -> listView.getChildren().add(new Label(employee.fullName)));
            this.getRoot().setVisible(true);
        }
    }
}
