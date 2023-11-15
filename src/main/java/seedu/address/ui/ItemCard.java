package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of an {@code Item}.
 */
public class ItemCard extends UiPart<Region> {

    private static final String FXML = "ListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label rating;
    @FXML
    private Label price;
    @FXML
    private Label cardDetails;

    /**
     * Creates a {@code PersonCode} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        name.setText(item.getName().fullName);
        rating.setText(item.getItemRatingString());
        price.setText("Price: ".concat(item.getPriceString()));
    }
}
