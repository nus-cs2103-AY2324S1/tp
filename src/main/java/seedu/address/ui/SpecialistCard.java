package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.Specialist;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class SpecialistCard extends PersonCard {

    @javafx.fxml.FXML
    private Label specialty;

    private static final String FXML = "SpecialistListCard.fxml";

    public SpecialistCard(Specialist specialist, int displayedIndex) {
        super(specialist, displayedIndex, FXML);
        specialty.setText(
                specialist
                .getSpecialty()
                .value);
    }
}
