package seedu.address.ui;

import javafx.scene.control.Label;
import seedu.address.model.person.Specialist;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class SpecialistCard extends PersonCard {
    private static final String FXML = "SpecialistListCard.fxml";
    @javafx.fxml.FXML
    private Label specialty;

    // Label is named as such due to 'location' being a reserved keyword.
    @javafx.fxml.FXML
    private Label spLocation;


    /**
     * Creates a {@code SpecialistCard} with the given {@code specialist} and index to display.
     */
    public SpecialistCard(Specialist specialist, int displayedIndex) {
        super(specialist, displayedIndex, FXML);
        spLocation.setText(specialist.getLocation().value);
        specialty.setText(
                specialist
                .getSpecialty()
                .value);
    }
}
