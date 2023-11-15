package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.RecipeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.testutil.IngredientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs(), new RecipeBook());
    }

    @Test
    public void execute_existingIngredientAddedTo_success() {
        Ingredient validIngredient = new IngredientBuilder().build();
        Ingredient expectedIngredient = new Ingredient(new Name("Flour"), new Quantity(150.0, Unit.GRAM));

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), new RecipeBook());
        expectedModel.addIngredient(validIngredient);
        assertCommandSuccess(new AddCommand(validIngredient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(expectedIngredient)),
                expectedModel);
    }

    @Test
    public void execute_newIngredient_success() {
        Ingredient addIngredient = new Ingredient(new Name("Lemon"), new Quantity(2, Unit.PIECE));
        Ingredient expectedIngredient = new Ingredient(new Name("Lemon"), new Quantity(2, Unit.PIECE));
        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), new RecipeBook());
        expectedModel.addIngredient(addIngredient);
        assertCommandSuccess(new AddCommand(addIngredient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(expectedIngredient)),
                expectedModel);
    }

    @Test
    public void execute_unitConversion_success() {
        Ingredient addIngredient = new Ingredient(new Name("Flour"), new Quantity(2, Unit.KILOGRAM));
        Ingredient expectedIngredient = new Ingredient(new Name("Flour"), new Quantity(2100, Unit.GRAM));
        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), new RecipeBook());
        expectedModel.addIngredient(addIngredient);
        assertCommandSuccess(new AddCommand(addIngredient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(expectedIngredient)),
                expectedModel);
    }


    @Test
    public void execute_unitConversion_throwsCommandException() {
        Ingredient addIngredient = new Ingredient(new Name("Flour"), new Quantity(2, Unit.PIECE));
        AddCommand addCommand = new AddCommand(addIngredient);
        assertCommandFailure(addCommand, model, "Unit PIECE cannot be converted to GRAM!");
    }
}
