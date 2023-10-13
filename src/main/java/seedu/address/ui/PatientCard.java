package seedu.address.ui;

import javafx.scene.control.Label;
import seedu.address.model.person.Patient;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends PersonCard {

    private static final String FXML = "PatientListCard.fxml";
    @javafx.fxml.FXML
    private Label medicalHistory;

    /**
     * Creates a {@code PatientCard} with the given {@code patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(patient, displayedIndex, FXML);
        medicalHistory.setText(
                patient.getMedicalHistory().value
        );
    }
}
