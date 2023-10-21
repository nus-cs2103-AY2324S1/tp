package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Employee;

/**
 * A UI component for displaying detailed information about an employee's profile. This component
 * displays the employee's name, phone number, address, email, salary, and the departments they belong to.
 */
public class ProfileDetails extends UiPart<Region> {
    private static final String FXML = "ProfileDetails.fxml";

    private Employee employee;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label salary;
    @FXML
    private FlowPane departments;

    /**
     * Creates a {@code EmployeeCode} with the given {@code Employee} and index to display.
     */
    public ProfileDetails() {
        super(FXML);
        this.getRoot().setVisible(false);
    }

    /**
     * Updates the details displayed in the profile component based on the provided {@code Employee} object.
     * If the employee is null, the component is hidden. Otherwise, it displays the employee's information,
     * including name, phone, address, email, salary, and departments.
     *
     * @param employee The {@code Employee} object for which to display the details.
     */
    public void updateDetails(Employee employee) {
        if (employee == null) {
            this.getRoot().setVisible(false);
        } else {
            name.setText(employee.getName().fullName);
            phone.setText(employee.getPhone().value);
            address.setText(employee.getAddress().value);
            email.setText(employee.getEmail().value);
            salary.setText(employee.getSalary().value);
            departments.getChildren().clear();
            employee.getDepartments().stream()
                .sorted(Comparator.comparing(department -> department.departmentName))
                .forEach(department -> departments.getChildren().add(new Label(department.departmentName)));
            this.getRoot().setVisible(true);
        }
    }
}
