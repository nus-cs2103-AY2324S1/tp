package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.testutil.RecipeBuilder;

public class RecipeIngredientNameMatchesPredicateTest {
    @Test
    public void equals() {
        String first = "flour";
        String second = "egg";

        RecipeIngredientNameMatchesPredicate firstPredicate = new RecipeIngredientNameMatchesPredicate(first);
        RecipeIngredientNameMatchesPredicate secondPredicate = new RecipeIngredientNameMatchesPredicate(second);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RecipeIngredientNameMatchesPredicate firstPredicateCopy = new RecipeIngredientNameMatchesPredicate(first);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different ingredient -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_recipeContainsIngredient_returnsTrue() {
        String name = "flour";
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate(name);
        Ingredient ingredient = new Ingredient(new Name(name), new Quantity(100, Unit.GRAM));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient(ingredient).build()));
    }

    @Test
    public void test_recipeDoesNotContainIngredient_returnsFalse() {
        String name = "flour";
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate(name);
        Ingredient ingredient = new Ingredient(new Name("egg"), new Quantity(100, Unit.GRAM));
        assertFalse(predicate.test(new RecipeBuilder().withIngredient(ingredient).build()));
    }

    @Test
    public void test_ingredientNotCaseSensitive_returnsTrue() {
        String name = "fLoUr";
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate(name);
        Ingredient ingredient = new Ingredient(new Name("flour"), new Quantity(100, Unit.GRAM));
        assertTrue(predicate.test(new RecipeBuilder().withIngredient(ingredient).build()));
    }

    @Test
    public void toStringMethod() {
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate("flour");
        String expected = RecipeIngredientNameMatchesPredicate.class.getCanonicalName() + "{name=flour}";
        assertEquals(expected, predicate.toString());
    }
}
