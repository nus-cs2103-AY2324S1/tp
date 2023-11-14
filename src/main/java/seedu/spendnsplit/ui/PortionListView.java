package seedu.spendnsplit.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.spendnsplit.model.person.Name;

/**
 * A UI component that displays information of a {@code Portion}.
 */
public class PortionListView extends UiPart<Region> {

    private static final String FXML = "PortionListView.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Name name;

    @FXML
    private HBox portionListView;
    @FXML
    private Label payer;
    @FXML
    private Label amount;
    @FXML
    private Label change;

    /**
     * Creates a {@code TransactionCode} with the given {@code Transaction} and index to display.
     */
    public PortionListView(Name name, String subtotal, String change, int displayedIndex) {
        super(FXML);
        this.name = name;
        NameLabel.setNameLabel(payer, name);
        amount.setText(subtotal);
        this.change.setText(change);
    }

}
