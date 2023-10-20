package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component for displaying detailed information about a person's profile. This component
 * displays the person's name, phone number, address, email, salary, and the departments they belong to.
 */
public class ProfileDetails extends UiPart<Region> {
    private static final String FXML = "ProfileDetails.fxml";

    private Person person;

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
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ProfileDetails() {
        super(FXML);
        this.getRoot().setVisible(false);
    }

    /**
     * Updates the details displayed in the profile component based on the provided {@code Person} object.
     * If the person is null, the component is hidden. Otherwise, it displays the person's information,
     * including name, phone, address, email, salary, and departments.
     *
     * @param person The {@code Person} object for which to display the details.
     */
    public void updateDetails(Person person) {
        if (person == null) {
            this.getRoot().setVisible(false);
        } else {
            name.setText(person.getName().fullName);
            phone.setText(person.getPhone().value);
            address.setText(person.getAddress().value);
            email.setText(person.getEmail().value);
            salary.setText(person.getSalary().value);
            departments.getChildren().clear();
            person.getDepartments().stream()
                .sorted(Comparator.comparing(department -> department.departmentName))
                .forEach(department -> departments.getChildren().add(new Label(department.departmentName)));
            this.getRoot().setVisible(true);
        }
    }
}
