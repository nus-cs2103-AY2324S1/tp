package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_MODIFY_RECIPE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeUuidMatchesPredicate;
import seedu.address.model.recipe.UniqueId;
import seedu.address.testutil.IngredientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ModifyCommand}.
 */
public class ModifyCommandTest {
    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());
    private Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());

    @Test
    public void execute_validArgumentsExistingIngredient_recipeModified() {
        UniqueId uuid = new UniqueId(1);

        // modify flour ingredient with a quantity of 50 grams
        Ingredient ingredient = new IngredientBuilder().withName("Flour").withQuantity(50, Unit.GRAM).build();
        ModifyCommand command = new ModifyCommand(uuid, ingredient);

        Recipe oldRecipe = model.getRecipe(uuid);
        expectedModel.deleteRecipe(oldRecipe);

        Recipe newRecipe = oldRecipe.modifyIngredients(ingredient.getName().fullName, ingredient);
        String expectedMessage = String.format(MESSAGE_MODIFY_RECIPE_SUCCESS, uuid);

        expectedModel.addRecipe(newRecipe);
        expectedModel.updateFilteredRecipeList(new RecipeUuidMatchesPredicate(uuid));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validArgumentsNewIngredient_recipeModified() {
        UniqueId uuid = new UniqueId(1);

        // add egg ingredient with a quantity of 50 grams
        Ingredient ingredient = new IngredientBuilder().withName("Egg").withQuantity(50, Unit.GRAM).build();
        ModifyCommand command = new ModifyCommand(uuid, ingredient);

        Recipe oldRecipe = model.getRecipe(uuid);
        expectedModel.deleteRecipe(oldRecipe);

        Recipe newRecipe = oldRecipe.addIngredient(ingredient);
        String expectedMessage = String.format(MESSAGE_MODIFY_RECIPE_SUCCESS, uuid);

        expectedModel.addRecipe(newRecipe);
        expectedModel.updateFilteredRecipeList(new RecipeUuidMatchesPredicate(uuid));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalid_recipeUuid() {
        UniqueId outOfBoundUuid = new UniqueId(3);
        Ingredient ingredient = new IngredientBuilder().withName("Egg").withQuantity(50, Unit.GRAM).build();
        ModifyCommand modifyCommand = new ModifyCommand(outOfBoundUuid, ingredient);
        assertCommandFailure(modifyCommand, model, Messages.MESSAGE_RECIPE_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        Ingredient ingredient = new IngredientBuilder().withName("Egg").withQuantity(50, Unit.GRAM).build();
        Ingredient ingredientSecond = new IngredientBuilder()
                .withName("Flour").withQuantity(50, Unit.GRAM).build();

        final ModifyCommand modifyCommand = new ModifyCommand(new UniqueId(1), ingredient);

        // same values -> returns true
        ModifyCommand commandWithSameValues = new ModifyCommand(new UniqueId(1), ingredient);
        assertTrue(modifyCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(modifyCommand.equals(modifyCommand));

        // null -> returns false
        assertFalse(modifyCommand.equals(null));

        // different types -> returns false
        assertFalse(modifyCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(modifyCommand.equals(new ModifyCommand(new UniqueId(2), ingredient)));

        // different descriptor -> returns false
        assertFalse(modifyCommand.equals(new ModifyCommand(new UniqueId(1), ingredientSecond)));
    }

    @Test
    public void toStringMethod() {
        Ingredient ingredient = new IngredientBuilder().withName("Egg").withQuantity(50, Unit.GRAM).build();
        ModifyCommand modifyCommand = new ModifyCommand(new UniqueId(1), ingredient);

        String expected = ModifyCommand.class.getCanonicalName() + "{uuid=" + 1 + ", toModify="
                + ingredient + "}";
        assertEquals(expected, modifyCommand.toString());
    }



}
