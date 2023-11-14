package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * UI component that displays a person in the display box.
 */
public class ViewPersonDisplay extends UiPart<Region> {
    private static final String FXML = "ViewPersonDisplay.fxml";

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
    private Label nameDisplayed;
    @FXML
    private Label phoneDisplayed;
    @FXML
    private Label emailDisplayed;
    @FXML
    private Label addressDisplayed;
    @FXML
    private FlowPane tagsDisplayed;

    /**
     * Displays a person's details in the viewing box.
     */
    public ViewPersonDisplay(Person person) {
        super(FXML);

        this.person = person;
        nameDisplayed.setText(person.getName().fullName);
        phoneDisplayed.setText(person.getPhone().value);
        emailDisplayed.setText(person.getEmail().value);
        addressDisplayed.setText(person.getAddress().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tagsDisplayed.getChildren().add(new Label(tag.tagName)));
    }
}
