package seedu.address.ui;

import seedu.address.model.person.Specialist;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class SpecialistCard extends PersonCard {

    private static final String FXML = "SpecialistListCard.fxml";

    public SpecialistCard(Specialist specialist, int displayedIndex) {
        super(specialist, displayedIndex, FXML);
    }
}
