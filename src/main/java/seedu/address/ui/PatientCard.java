package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.patients.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     * AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label gender;
    @FXML
    private Label birthdate;

    @FXML
    private Label remark;
    @FXML
    private Label treatment;
    @FXML
    private FlowPane patientId;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        name.setText(patient.getName().fullName);
        Label idLabel = new Label("ID: " + String.valueOf(patient.getId()));
        patientId.getChildren().add(idLabel);
        phone.setText("Phone: " + patient.getPhone().value);
        address.setText("Address: " + patient.getAddress().value);
        email.setText("Email: " + patient.getEmail().value);
        gender.setText("Gender: " + patient.getGender().value);
        birthdate.setText("Birthday: " + patient.getBirthdate().value);
        remark.setText("Remark: " + patient.getRemark().value);
        treatment.setText("Treatment: " + patient.getTreatmentName().value);

        patient.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
