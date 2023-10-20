package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

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
