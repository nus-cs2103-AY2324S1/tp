package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BUTTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Inventory;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Unit;

/**
 * A utility class containing a list of {@code Ingredient} objects to be used in tests.
 */
public class TypicalIngredients {

    public static final Ingredient FLOUR = new IngredientBuilder()
            .withName("Flour").withQuantity(100.0, Unit.GRAM).build();
    public static final Ingredient EGG = new IngredientBuilder()
            .withName("Egg").withQuantity(100.0, Unit.PIECE).build();

    // Manually added - Ingredient's details found in {@code CommandTestUtil}
    public static final Ingredient BUTTER = new IngredientBuilder().withName(VALID_NAME_BUTTER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code Inventory} with all the typical ingredients.
     */
    public static Inventory getTypicalInventory() {
        Inventory ab = new Inventory();
        for (Ingredient ingredient : getTypicalIngredients()) {
            ab.addIngredient(ingredient);
        }
        return ab;
    }

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(FLOUR, EGG));
    }
}
