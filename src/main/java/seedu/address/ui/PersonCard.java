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
    private Label nric;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label age;
    @FXML
    private FlowPane allergies;
    @FXML
    private Label gender;
    @FXML
    private Label bloodType;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        nric.setText(person.getNric().nric);
        phone.setText(person.getPhone().value);
        age.setText(String.valueOf(person.getAge().age));
        gender.setText(person.getGender().gender);
        email.setText(person.getEmail().value);
        bloodType.setText(person.getBloodType().bloodType);

        person.getAllergies().stream()
                .sorted(Comparator.comparing(allergy -> allergy.allergy))
                .forEach(allergy ->
                {
                    Label label = new Label(allergy.allergy + " ");
                    label.setWrapText(true);
                    label.setMaxWidth(100);
                    allergies.getChildren().add(label);
                });
    }
}
