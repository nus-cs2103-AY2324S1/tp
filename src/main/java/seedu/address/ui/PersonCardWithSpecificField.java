package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays specific information of a {@code Person}.
 */
public class PersonCardWithSpecificField extends UiPart<Region> {

    private static final String FXML = "SpecificPersonListCard.fxml";

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
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label fieldLabel;

    /**
     * Creates a {@code PersonCode} with the given {@code Person},index and specific
     * field to display.
     */
    public PersonCardWithSpecificField(Person person, int displayedIndex, String fieldToRead) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        fieldLabel.setText(fieldToRead);
    }

}
