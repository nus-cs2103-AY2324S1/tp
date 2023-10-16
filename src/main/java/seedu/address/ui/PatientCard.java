package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.address.model.person.Patient;


/**
 * A UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends PersonCard {

    private static final String card = "PatientListCard.fxml";
    @FXML
    private Label age;
    @FXML
    private FlowPane medicalHistory;

    /**
     * Creates a {@code PatientCard} with the given {@code patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(patient, displayedIndex, card);
        age.setText(patient.getAge().value);
        patient.getMedicalHistory().stream()
                .sorted(Comparator.comparing(medicalHistory -> medicalHistory.value))
                .forEach(medicalHistory -> this.medicalHistory.getChildren().add(new Label(medicalHistory.value)));
    }
}
