package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.animal.Animal;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class AnimalCard extends UiPart<Region> {

    private static final String FXML = "AnimalListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Animal animal;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label petId;
    @FXML
    private Label sex;
    @FXML
    private Label species;
    @FXML
    private Label breed;



    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AnimalCard(Animal animal, int displayedIndex) {
        super(FXML);
        this.animal = animal;
        id.setText(displayedIndex + ". ");
        name.setText(animal.getName().fullName);
        petId.setText("ID: " + animal.getPetId().toString());
        sex.setText("Sex: " + animal.getSex().toString());
        species.setText("Species: " + animal.getSpecies().toString());
        breed.setText("Breed: " + animal.getBreed().toString());
    }
}
