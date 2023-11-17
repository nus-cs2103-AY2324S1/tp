package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.animal.Animal;

/**
 * UI component that displays detailed animal information when selected.
 */
public class AnimalDetailPanel extends UiPart<Region> {
    private static final String FXML = "AnimalDetailPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private Label petId;
    @FXML
    private Label dateOfBirth;

    @FXML
    private Label age;
    @FXML
    private Label sex;
    @FXML
    private Label breed;

    @FXML
    private Label species;
    @FXML
    private Label admissionDate;

    @FXML
    private Label tasks;

    /**
     * Creates an empty detailed view panel upon initialization.
     */
    public AnimalDetailPanel() {
        super(FXML);
        clearDetails();
    }

    /**
     * Update the UI with the details of the provided person.
     */
    public void updateDetails(Animal animal) {
        name.setText("Name: " + animal.getName().fullName);
        petId.setText("ID: " + animal.getPetId().toString());
        sex.setText("Sex: " + animal.getSex().toString());
        species.setText("Species: " + animal.getSpecies().toString());
        breed.setText("Breed: " + animal.getBreed().toString());
        dateOfBirth.setText("Date of Birth: " + animal.getDateOfBirth().toString());
        age.setText("Age: " + animal.getDateOfBirth().getAge());
        admissionDate.setText("Date of Admission: " + animal.getAdmissionDate().toString());
        if (animal.getTaskList().isEmpty()) {
            tasks.setText("Tasks: None");
        } else {
            tasks.setText("Tasks: \n" + animal.getTaskList().toString());
        }
    }

    /**
     * Clear the details (For displaying a blank panel)
     */
    public void clearDetails() {
        name.setText("");
        petId.setText("");
        dateOfBirth.setText("");
        age.setText("");
        sex.setText("");
        breed.setText("");
        species.setText("");
        admissionDate.setText("");
        tasks.setText("");
    }
}
