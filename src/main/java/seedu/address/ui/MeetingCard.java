package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "MeetingScheduleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label l;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label dateStart;
    @FXML
    private Label start;
    @FXML
    private Label spacer;
    @FXML
    private Label end;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MeetingCode} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        title.setText(meeting.getTitle().meetingTitle);
        l.setText(meeting.getLocation().location);
        LocalDateTime temp = meeting.getStart();
        dateStart.setText(temp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        start.setText(temp.format(DateTimeFormatter.ofPattern("HHmm")));
        spacer.setText("-");
        end.setText(meeting.getEnd().format(DateTimeFormatter.ofPattern("HHmm")));
        meeting.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
