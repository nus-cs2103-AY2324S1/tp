package swe.context.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import swe.context.model.contact.Contact;

/**
 * An UI component that displays information of a {@code Contact}.
 */
public class ContactCard extends UiPart<Region> {

    private static final String FXML = "ContactListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Contact contact;

    @FXML private HBox cardPane;

    @FXML private Label id;
    @FXML private Label name;
    @FXML private Label phone;
    @FXML private Label email;
    @FXML private Label note;

    @FXML private FlowPane tags;
    @FXML private FlowPane alternates;

    /**
     * Creates a {@code PersonCode} with the given {@code Contact} and index to display.
     */
    public ContactCard(Contact contact, int displayedIndex) {
        super(FXML);
        this.contact = contact;

        id.setText("#" + displayedIndex);
        name.setText(contact.getName().value);
        phone.setText(contact.getPhone().value);
        email.setText(contact.getEmail().value);

        String noteText = contact.getNote().value;
        if (!noteText.isEmpty()) {
            note.setVisible(true);
            note.setManaged(true);
            note.setText(contact.getNote().value);
        } else {
            note.setVisible(false);
            note.setManaged(false);
        }

        contact.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.value))
                .forEach(tag -> tags.getChildren().add(new Label(tag.value)));

        contact.getAlternates().stream()
                .sorted(Comparator.comparing(alternate -> alternate.value))
                .forEach(alternate -> alternates.getChildren().add(new Label(alternate.value)));
    }
}
