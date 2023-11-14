package seedu.address.ui;

import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on NpcTrack level 4</a>
     */

    public final Person person;
    @FXML
    private HBox cardPane;
    @FXML
    private Hyperlink name;
    @FXML
    private Label id;
    @FXML
    private Label attendance;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    @FXML
    private Label participation;
    @FXML
    private Hyperlink telegramLink;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane comments;
    @FXML
    private FlowPane assignments;
    @FXML
    private Label group;
    private final String absentEmoji = "X";
    private final String presentEmoji = ":)";
    private final String vrEmoji = ":/";
    private final String unknownEmoji = "?";

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        String telegramAddress = person.getTelegramHandle() != null ? " (@" + person.getTelegramHandle().value + ")"
                : " -";
        name.setText(person.getName().fullName + telegramAddress);
        attendance.setText(String.format("Attendance: %d/%d  %s",
                this.person.getAttendance().getWeeksPresent(), this.person.getAttendance().getTotalMarkedTut(),
                this.person.getAttendance().getStyledStatusList(absentEmoji, presentEmoji,
                        vrEmoji, unknownEmoji)));
        participation.setText(String.format("Participation Points: %d",
                    this.person.getTotalPart()));
        String phoneString = person.getPhone() != null ? person.getPhone().value : " -";
        phone.setText("Phone: " + phoneString);
        String emailString = person.getEmail() != null ? person.getEmail().value : " -";
        email.setText("Email: " + emailString);
        group.setText("Tutorial Group: " + person.getGroup().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getComments().stream()
                .sorted(Comparator.comparing(comment -> comment.commentName))
                .forEach(comment -> comments.getChildren().add(new Label(comment.commentName)));
        person.getAssignments().stream()
                .sorted(Comparator.comparing(assignment -> assignment.toString()))
                .forEach(assignment -> assignments.getChildren().add(new Label(assignment.toString())));
    }

    /**
     * Opens a link to the telegram handle of {@code Person}.
     */
    @FXML
    public void openTelegram(ActionEvent event) {
        if (person.getTelegramHandle() == null) {
            System.out.println("No Telegram handle available.");
            return;
        }

        String telegramHandle = person.getTelegramHandle().value;
        UiUtil.openTelegram(telegramHandle);
    }
}
