package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Panel containing detailed information for person and meeting.
 */
public class InfoDisplayPanel extends UiPart<Region> {
    private static final String FXML = "InfoDisplayPanel.fxml";

    @FXML
    private Label infoDisplay;

    /**
     * Creates a text display with the given {@code person}.
     */
    public InfoDisplayPanel() {
        super(FXML);
    }

    public void setViewedPerson(Person person) {
        if (person == null) {
            infoDisplay.setText("Nothing!");
        } else {
            infoDisplay.setText(person.toDisplayString());
        }
    }
}
