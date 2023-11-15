package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.ingredient.Ingredient;

/**
 * An UI component that displays information of a {@code Ingredient}.
 */
public class IngredientCard extends UiPart<Region> {

    private static final String FXML = "IngredientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Ingredient ingredient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label quantity;
    @FXML
    private Label unit;

    /**
     * Creates a {@code IngredientCode} with the given {@code Ingredient} and index to display.
     */
    public IngredientCard(Ingredient ingredient, int displayedIndex) {
        super(FXML);
        this.ingredient = ingredient;
        name.setText(this.ingredient.getName().fullName.toUpperCase());
        //todo un-elegant code
        String[] quantityAndUnit = this.ingredient.getQuantity().toString().split(" ");
        String quantityStr = quantityAndUnit[0];
        String unitStr = quantityAndUnit[1];
        quantity.setText(quantityStr);
        unit.setText(unitStr);
    }
}
