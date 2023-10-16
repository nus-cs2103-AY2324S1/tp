package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.meeting.Meeting;
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

    public void setViewedPerson(Pair<Person, Meeting> pair) {
        Person person = pair.getKey();
        Meeting meeting = pair.getValue();

        String personDetails = "";
        String meetingDetails = "";

        if (person != null) {
            personDetails = person.toDisplayString();
        }
        if (meeting != null) {
            meetingDetails = meeting.toDisplayString();
        }

        infoDisplay.setText(personDetails + "\n\n" + meetingDetails);
    }
}
