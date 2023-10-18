package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.musician.Musician;

/**
 * An UI component that displays information of a {@code Musician}.
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

    public final Musician musician;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane instruments;
    @FXML
    private FlowPane genres;

    /**
     * Creates a {@code PersonCode} with the given {@code Musician} and index to display.
     */
    public PersonCard(Musician musician, int displayedIndex) {
        super(FXML);
        this.musician = musician;
        id.setText(displayedIndex + ". ");
        name.setText(musician.getName().fullName);
        phone.setText(musician.getPhone().value);
        email.setText(musician.getEmail().value);
        musician.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        musician.getInstruments().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> instruments.getChildren().add(new Label(tag.tagName)));
        musician.getGenres().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> genres.getChildren().add(new Label(tag.tagName)));
    }
}
