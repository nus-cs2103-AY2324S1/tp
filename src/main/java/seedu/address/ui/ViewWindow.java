package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ViewWindow extends UiPart<Region> {

    private static final String FXML = "ViewWindow.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final Logger logger = LogsCenter.getLogger(ViewWindow.class);

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label keyMilestone;
    @FXML
    private ImageView keyMilestoneIcon;
    @FXML
    private ImageView meetingTimeIcon;
    @FXML
    private Label keyMilestoneTitle;
    @FXML
    private Label meetingTimeTitle;
    @FXML
    private HBox meetingTimeBox;
    @FXML
    private Label meetingTime;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ViewWindow(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        //id.setText(displayedIndex + ".")
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        logger.info("person" + person.isLead());
        logger.info("has meeting time:" + person.getMeetingTime().isPresent());
        if (person.isLead()) {
            Lead convertedPerson = (Lead) person;
            keyMilestoneIcon.setVisible(true);
            keyMilestone.setManaged(true);
            keyMilestoneTitle.setManaged(true);
            keyMilestone.setText(convertedPerson.getKeyMilestone().value);
        } else {
            keyMilestoneIcon.setVisible(false);
            keyMilestone.setManaged(false);
            keyMilestoneTitle.setManaged(false);
        }

        if (person.getMeetingTime().isPresent()) {
            meetingTime.setText(person.getMeetingTimeString());
            meetingTimeTitle.setManaged(true);
            meetingTime.setManaged(true);
            meetingTimeIcon.setVisible(true);
        } else {
            meetingTimeIcon.setVisible(false);
            meetingTimeBox.setManaged(false);
            meetingTime.setManaged(false);
            meetingTimeTitle.setManaged(false);
        }
        Label label = new Label(person.getType().value);
        if (person.isClient()) {
            label.getStyleClass().add("client-label");
        } else {
            label.getStyleClass().add("lead-label");
        }
        tags.getChildren().add(label);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
