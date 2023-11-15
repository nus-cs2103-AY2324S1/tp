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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label animalName;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        if (!person.getHousing().equals(null) && !"nil".equals(person.getHousing().value)) {
            Label housingLabel = new Label(person.getHousing().value);
            housingLabel.setStyle("-fx-background-color: #784c87;");
            tags.getChildren().add(housingLabel);
        }

        if (!person.getAvailability().equals(null) && !"nil".equals(person.getAvailability().value)) {
            Label availabilityLabel = new Label(person.getAvailability().value);
            if ("Available".equals(person.getAvailability().value)) {
                availabilityLabel.setStyle("-fx-background-color: #55874c;");
            }
            if ("NotAvailable".equals(person.getAvailability().value)) {
                availabilityLabel.setStyle("-fx-background-color: #874c53;");
            }
            tags.getChildren().add(availabilityLabel);
        }

        if (!person.getAnimalType().equals(null) && !"nil".equals(person.getAnimalType().value)) {
            Label animalTypeLabel = new Label(person.getAnimalType().value);
            animalTypeLabel.setStyle("-fx-background-color: #87854c");
            tags.getChildren().add(animalTypeLabel);
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        animalName.setText(!person.getAnimalName().equals(null) ? "Fostering: " + person.getAnimalName().fullName
                : "Fostering: nil");
    }
}
