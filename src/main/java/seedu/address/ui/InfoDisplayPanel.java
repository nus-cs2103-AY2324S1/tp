package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
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
    private Label personDisplay;
    @FXML
    private FlowPane tags;
    @FXML
    private Label meetingDisplay;

    /**
     * Creates a text display with the given {@code person}.
     */
    public InfoDisplayPanel() {
        super(FXML);
    }

    public void setViewedModel(Pair<Person, Meeting> pair) {
        Person person = pair.getKey();
        Meeting meeting = pair.getValue();
        tags.getChildren().clear();

        if (person != null) {
            personDisplay.setText(person.toDisplayString());
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            personDisplay.setText("");
        }

        if (meeting != null) {
            meetingDisplay.setText(meeting.toDisplayString());
        } else {
            meetingDisplay.setText("");
        }
    }
}
