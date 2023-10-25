package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

/**
 * Panel containing the person details.
 */
public class ViewPersonPanel extends UiPart<Region> {
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private Label personType;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane viewTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ViewPersonPanel(Person person, String fxml) {
        super(fxml);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(": " + person.getPhone().value);
        email.setText(": " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> viewTags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Update the person that are going to be viewed.
     *
     * @param person the person that is going to be viewed
     * @return updated version of ViewPersonPanel
     */
    public static ViewPersonPanel updatePerson(Person person) {
        if (person == null) {
            return null;
        }
        if (person instanceof Patient) {
            return new ViewPatientPanel((Patient) person);
        } else {
            return new ViewSpecialistPanel((Specialist) person);
        }
    }
}

