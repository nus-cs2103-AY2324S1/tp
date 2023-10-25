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
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane phone;
    @FXML
    private FlowPane address;
    @FXML
    private FlowPane email;
    @FXML
    private FlowPane birthday;
    @FXML
    private FlowPane groups;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (!person.getPhone().value.isEmpty()) {
            phone.getChildren().add(new Label(person.getPhone().value));
        }
        if (!person.getAddress().value.isEmpty()) {
            address.getChildren().add(new Label(person.getAddress().value));
        }
        if (!person.getEmail().value.isEmpty()) {
            email.getChildren().add(new Label(person.getEmail().value));
        }
        if (!person.getBirthday().toString().isEmpty()) {
            birthday.getChildren().add(new Label(person.getBirthday().toString()));
        }
        person.getGroups().stream()
                .sorted(Comparator.comparing(group -> group.groupName))
                .forEach(group -> groups.getChildren().add(new Label(group.groupName)));
    }
}
