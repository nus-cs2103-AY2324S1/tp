package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.stall.Stall;

/**
 * An UI component that displays information of a {@code Stall}.
 */
public class StallCard extends UiPart<Region> {

    private static final String FXML = "ListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Stall stall;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label cardDetails;

    @FXML
    private Label rating;

    @FXML
    private Label price;

    /**
     * Creates a {@code PersonCode} with the given {@code Stall} and index to display.
     */
    public StallCard(Stall stall, int displayedIndex) {
        super(FXML);
        this.stall = stall;
        id.setText(displayedIndex + ". ");
        name.setText(stall.getName().fullName);
        rating.setText(stall.getStallStarRating());
        cardDetails.setText("Location: ".concat(stall.getLocation().locationName));
        price.setText(stall.getAveragePriceString());
    }
}
