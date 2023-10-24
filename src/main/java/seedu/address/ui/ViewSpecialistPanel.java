package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.Specialist;

public class ViewSpecialistPanel extends ViewPersonPanel{
    private static final String card = "ViewSpecialistPanel.fxml";
    @FXML
    private Label specialty;

    // Label is named as such due to 'location' being a reserved keyword.
    @FXML
    private Label spLocation;


    /**
     * Creates a {@code SpecialistCard} with the given {@code specialist} and index to display.
     */
    public ViewSpecialistPanel(Specialist specialist) {
        super(specialist, card);
        spLocation.setText(": " + specialist.getLocation().value);
        specialty.setText(": " + specialist.getSpecialty().value);
    }
}
