package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
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
    private Label linkedin;
    @FXML
    private Label secondaryEmail;
    @FXML
    private Label birthday;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane tags;
    @FXML
    private Button notesButton;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        bindLabelToProperty(name, person.getName().fullName);
        bindLabelToProperty(phone, person.getPhone().value);
        bindLabelToProperty(address, person.getAddress().value);
        bindLabelToProperty(email, person.getEmail().value);
        bindLabelToProperty(linkedin, person.getLinkedin().map(l -> l.value).orElse(""));
        bindLabelToProperty(secondaryEmail, person.getSecondaryEmail().map(e -> e.value).orElse(""));
        bindLabelToProperty(telegram, person.getTelegram().map(t -> t.value).orElse(""));
        bindLabelToProperty(birthday, person.getBirthday().map(b -> b.toString()).orElse(""));
        person.getEmergencyTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    tags.getChildren().add(label);
                });
        person.getNonEmergencyTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        int numberOfNotes = person.getNotes().size();
        notesButton.setText("Notes (" + numberOfNotes + ")");
    }

    private void bindLabelToProperty(Label label, String propertyValue) {
        label.setText(propertyValue);
        label.visibleProperty().bind(label.textProperty().isNotEmpty());
        label.managedProperty().bind(label.visibleProperty());
    }

    /**
     * Opens a new window to display the notes of the person.
     */
    @FXML
    public void handleNotesButtonClick() {
        try {
            NotesWindow notesWindow = new NotesWindow(person);
            notesWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
