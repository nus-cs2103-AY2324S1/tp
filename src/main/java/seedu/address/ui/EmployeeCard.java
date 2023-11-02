package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.employee.Employee;

/**
 * A UI component that displays information of a {@code Employee}.
 */
public class EmployeeCard extends UiPart<Region> {

    private static final String FXML = "EmployeeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Employee employee;

    @FXML
    private HBox cardPane;
    @FXML
    private GridPane cardEmployee;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label position;
    @FXML
    private Label employeeId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label salary;
    @FXML
    private FlowPane departments;
    @FXML
    private Label isOnLeave;

    /**
     * Creates a {@code EmployeeCode} with the given {@code EmployeeCode} and index to display.
     */
    public EmployeeCard(Employee employee, int displayedIndex) {
        super(FXML);
        this.employee = employee;

        cardPane.setPadding(new Insets(3));
        // alternate cardPane colour
        if (displayedIndex % 2 == 0) {
            cardPane.setStyle("-fx-background-color: #6b77b5; -fx-border-width: 1px; " +
                    "-fx-background-radius: 10px; -fx-border-color: transparent; -fx-border-radius: 10px; " +
                    "-fx-border-insets: 5px; -fx-background-insets: 5px");
        } else {
            cardPane.setStyle("-fx-background-color: #576CBC; -fx-border-width: 1px; " +
                    "-fx-background-radius: 10px; -fx-border-color: transparent; -fx-border-radius: 10px; " +
                    "-fx-border-insets: 5px; -fx-background-insets: 5px");
        }

        id.setText(displayedIndex + ". ");
        name.setText(employee.getName().fullName);
        position.setText(employee.getPosition().value);
        employeeId.setText(employee.getId().value);
        phone.setText(employee.getPhone().toString());
        email.setText(employee.getEmail().value);
        salary.setText(employee.getSalary().toString());
        VBox.setMargin(isOnLeave, new Insets(1, 0, 1, 4));
        VBox.setMargin(departments, new Insets(1, 0, 1, 4));
        employee.getDepartments().stream()
                .sorted(Comparator.comparing(department -> department.departmentName))
                .forEach(department -> departments.getChildren().add(new Label(department.departmentName)));
        if (employee.isOnLeaveToday()) {
            isOnLeave.setText("On Leave");
            isOnLeave.setStyle("-fx-background-color: #b33940; -fx-text-fill: #ffffff; -fx-background-radius: 2; -fx-padding: 1 3 1 3;");
        } else {
            isOnLeave.setText("Present");
            isOnLeave.setStyle("-fx-background-color: #62a662; -fx-text-fill: #ffffff; -fx-background-radius: 2; -fx-padding: 1 3 1 3;");
        }
    }
}
