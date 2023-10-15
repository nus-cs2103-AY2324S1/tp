package networkbook.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String PRIORITY_HEADER = "Priority: ";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/networkbook-level4/issues/336">The issue on NetworkBook level 4</a>
     */

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
    private Label link;
    @FXML
    private Label graduatingYear;
    @FXML
    private Label course;
    @FXML
    private Label specialisation;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label priority;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        link.setText(person.getLinks().toString());
        graduatingYear.setText(person.getGraduatingYear().value);
        email.setText(person.getEmails().toString());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getPriority().ifPresentOrElse((Priority p) ->
                        priority.setText(PRIORITY_HEADER + p), () -> priority.setVisible(false));
    }

    public Label getPriority() {
        return priority; // getter method for testing
    }
}
