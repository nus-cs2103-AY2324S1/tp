package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.recipe.Recipe;

/**
 * Panel containing the list of recipes.
 */
public class FullRecipePanel extends UiPart<Region> {
    private static final String FXML = "FullRecipePanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Recipe recipe;

    private final Logger logger = LogsCenter.getLogger(RecipeListPanel.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label ingredients;
    @FXML
    private Label steps;

    /**
     * Creates a {@code IngredientCode} with the given {@code Ingredient} and index to display.
     */
    public FullRecipePanel(Recipe recipe) { // To be updated
        super(FXML);
        this.recipe = recipe;
        name.setText(recipe.getName().toString());
        id.setText(" (uuid: " + recipe.getId() + ")");
        ingredients.setText(recipe.getIngredientsText());
        steps.setText(recipe.getStepsText());
    }
}
