package wedlog.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import wedlog.address.model.person.Guest;

/**
 * A UI component that displays information of a {@code Guest}.
 */
public class GuestCard extends UiPart<Region> {

    private static final String FXML = "GuestListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final String RSVP_YES = "yes";
    private static final String RSVP_NO = "no";

    public final Guest guest;

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
    private Label rsvpStatusPlaceholder;
    @FXML
    private Label rsvpStatus;
    @FXML
    private FlowPane dietaryRequirements;
    @FXML
    private Label tableNumber;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code GuestCode} with the given {@code Guest} and index to display.
     */
    public GuestCard(Guest guest, int displayedIndex) {
        super(FXML);
        this.guest = guest;
        id.setText(displayedIndex + ". ");
        name.setText(guest.getName().fullName);
        phone.setText(guest.getPhone().map(p -> p.value).orElse(""));
        address.setText(guest.getAddress().map(a -> a.value).orElse(""));
        email.setText(guest.getEmail().map(e -> e.value).orElse(""));
        tableNumber.setText(guest.getTableNumber().map(tn -> "table " + tn.value).orElse(""));

        phone.setManaged(!phone.getText().isEmpty());
        email.setManaged(!email.getText().isEmpty());
        address.setManaged(!address.getText().isEmpty());
        tableNumber.setManaged(!tableNumber.getText().isEmpty());

        // Setting the RSVP Label with conditional styling
        rsvpStatus.setText(Optional.ofNullable(guest.getRsvpStatus()).map(r -> "RSVP: " + r.value).orElse(""));
        switch (guest.getRsvpStatus().value) {
        case RSVP_YES:
            rsvpStatus.setStyle("-fx-background-color: #6aa84f");
            break;
        case RSVP_NO:
            rsvpStatus.setStyle("-fx-background-color: #cc4125");
            break;
        default:
            rsvpStatus.setStyle("-fx-background-color: #ffa42f");
        }

        guest.getDietaryRequirements().stream()
                .sorted(Comparator.comparing(dietaryRequirement -> dietaryRequirement.value))
                .forEach(dietaryRequirement -> dietaryRequirements.getChildren()
                        .add(new Label(dietaryRequirement.value)));

        guest.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
