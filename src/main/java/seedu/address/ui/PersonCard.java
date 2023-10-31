package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
    private Label uniqueId;
    @FXML
    private ImageView avatar;
    @FXML
    private Button notesButton;
    @FXML
    private Label balance;
    @FXML
    private Label phoneField;
    @FXML
    private Label addressField;
    @FXML
    private Label emailField;
    @FXML
    private Label linkedinField;
    @FXML
    private Label birthdayField;
    @FXML
    private Label secondaryEmailField;
    @FXML
    private Label telegramField;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        phoneField.setText("Tel:");
        addressField.setText("Room Number:");
        emailField.setText("Email:");
        linkedinField.setText("Linkedin:");
        birthdayField.setText("Birthday:");
        secondaryEmailField.setText("Second Email:");
        telegramField.setText("Telegram:");
        name.setText(person.getName().fullName);
        bindLabelToProperty(phone, person.getPhone().value, phoneField);
        bindLabelToProperty(address, person.getAddress().value, addressField);
        bindLabelToProperty(email, person.getEmail().value, emailField);
        bindLabelToProperty(linkedin, person.getLinkedin().map(l -> l.value).orElse(""), linkedinField);
        bindLabelToProperty(secondaryEmail,
                person.getSecondaryEmail().map(e -> e.value).orElse(""),
                secondaryEmailField);
        bindLabelToProperty(telegram, person.getTelegram().map(t -> t.value).orElse(""), telegramField);
        bindLabelToProperty(birthday, person.getBirthday().map(b -> b.toString()).orElse(""), birthdayField);
        avatar.setImage(person.getAvatar().getImage());
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
        StringBinding notesButtonText = Bindings
                .createStringBinding(() -> "Notes (" + person.getNotes().size() + ")",
                        person.getNotes()); // This will cause the binding to update when the list changes
        notesButton.textProperty().bind(notesButtonText);
        balance.setText(person.getBalance().toUiMessage());
    }

    private void bindLabelToProperty(Label label, String propertyValue, Label labelField) {
        label.setText(propertyValue);
        label.visibleProperty().bind(label.textProperty().isNotEmpty());
        label.managedProperty().bind(label.visibleProperty());
        if (!label.getText().isEmpty()) {
            labelField.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(4),
                    new Insets(-3, -3, -3, -3))));
            HBox parent = (HBox) labelField.getParent();
            parent.setMargin(labelField, new Insets(0, 10, 8, 3));
        } else {
            labelField.setText("");
            labelField.visibleProperty().bind(labelField.textProperty().isNotEmpty());
            labelField.managedProperty().bind(labelField.visibleProperty());
        }
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

