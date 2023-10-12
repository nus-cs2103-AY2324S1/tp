package seedu.address.ui;

import seedu.address.model.person.Patient;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends PersonCard {

    private static final String FXML = "PatientListCard.fxml";

    public PatientCard(Patient patient, int displayedIndex) {
        super(patient, displayedIndex, FXML);
    }
}
