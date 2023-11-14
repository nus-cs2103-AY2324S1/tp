package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.ui.labels.SkillLabel;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class VolunteerCard extends UiPart<Region> {

    private static final String FXML = "VolunteerListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Volunteer volunteer;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane skills;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public VolunteerCard(Volunteer volunteer, int displayedIndex) {
        super(FXML);
        this.volunteer = volunteer;
        id.setText(displayedIndex + ". ");
        name.setText(volunteer.getName().fullName);
        phone.setText("Phone number: " + volunteer.getPhone().value);
        email.setText("Email: " + volunteer.getEmail().value);
        volunteer.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new SkillLabel(skill).getRoot()));
    }
}
