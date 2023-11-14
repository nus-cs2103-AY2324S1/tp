package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.displayable.DisplayableListViewItem;

/**
 * An UI component that displays information of a {@code DisplayableListViewItem}.
 */
public class DisplayCard extends UiPart<Region> {

    private static final String FXML = "DisplayableListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final DisplayableListViewItem item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label displayTitle;
    @FXML
    private Label id;
    @FXML
    private Label firstText;
    @FXML
    private Label secondText;

    /**
     * Creates a {@code PersonCode} with the given {@code Event} and index to display.
     */
    public DisplayCard(DisplayableListViewItem item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        displayTitle.setText(item.getDisplayTitle());
        firstText.setText(item.getDisplayFirstText());
        secondText.setText(item.getDisplaySecondText());
    }
}
