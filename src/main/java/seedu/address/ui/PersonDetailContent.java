package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetailContent extends UiPart<Region> {

    private static final String FXML = "PersonDetailContent.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label gpa;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label previousGrade;
    @FXML
    private Label interviewScore;
    @FXML
    private Label comment;
    @FXML
    private FlowPane attachments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonDetailContent(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        gpa.setText(person.getGpa().toString());
        previousGrade.setText(person.getPreviousGrade().toString());
        interviewScore.setText(person.getInterviewScore().map(score -> score.toString()).orElse(""));
        comment.setText(person.getComment().map(comment -> comment.comment).orElse(""));
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getAttachments().stream()
                .sorted(Comparator.comparing(attachment -> attachment.toString()))
                .forEach(attachment -> attachments.getChildren()
                        .add(new Label(attachment.file.toPath().getFileName().toString())));
    }
}
