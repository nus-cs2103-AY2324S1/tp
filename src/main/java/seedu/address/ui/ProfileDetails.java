package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;

/**
 * A UI component for displaying detailed information about an employee's profile. This component
 * displays the employee's name, phone number, address, email, salary, and the departments they belong to.
 */
public class ProfileDetails extends UiPart<Region> {
    private static final String FXML = "ProfileDetails.fxml";

    private Employee employee;
    private Department department;
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
    private FlowPane listView;
    @FXML
    private FlowPane roleLarge;
    @FXML
    private Label leave;
    @FXML
    private FlowPane listSupervisors;

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
            listView.getChildren().clear();
            roleLarge.getChildren().clear();
            listSupervisors.getChildren().clear();
            leave.setText(employee.getLeave().value);
            employee.getDepartments().stream()
                .sorted(Comparator.comparing(department -> department.fullName))
                .forEach(department -> listView.getChildren().add(new Label(department.fullName)));
            employee.getSupervisors().stream()
                    .sorted(Comparator.comparing(supervisor -> supervisor.fullName))
                    .forEach(supervisor -> listSupervisors.getChildren().add(new Label(supervisor.fullName)));
            roleLarge.getChildren().add(new Label(employee.getRole().toString()));
            this.getRoot().setVisible(true);
        }
    }
}
