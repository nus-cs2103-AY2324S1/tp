package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertIngredientFailure;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.testutil.IngredientBuilder;

public class UseCommandTest {
    private String expectedErrorMessage = "Ingredient does not exist in Inventory ";
    @Test
    public void execute_emptyInventory_throwsIngredientNotFoundException() {
        Model model = new ModelManager();
        UseCommand command = new UseCommand(new Name("Flour"), new Quantity(50, Unit.GRAM));
        assertIngredientFailure(command, model, expectedErrorMessage);
    }

    @Test
    public void execute_ingredientDoesNotExist_throwsIngredientNotFoundException() {
        Model model = new ModelManager();
        UseCommand command = new UseCommand(new Name("Egg"), new Quantity(50, Unit.GRAM));
        model.addIngredient(new IngredientBuilder().build());
        assertIngredientFailure(command, model, expectedErrorMessage);
    }

    @Test
    public void execute_ingredientExistsAndCompletelyRemoved_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String message = "Ingredient used: Flour; \nQuantity Left: 0.0 GRAM\nIngredient Removed";
        UseCommand command = new UseCommand(new Name("Flour"), new Quantity(50, Unit.GRAM));
        model.addIngredient(new IngredientBuilder().build());
        expectedModel.addIngredient(new IngredientBuilder().build());
        expectedModel.useIngredient(new Name("Flour"), new Quantity(50.0, Unit.GRAM));
        assertCommandSuccess(command, model, message, expectedModel);
    }

    @Test
    public void execute_ingredientExistsAndUsed_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String message = "Ingredient used: Flour; \nQuantity Left: 30.0 GRAM";
        UseCommand command = new UseCommand(new Name("Flour"), new Quantity(20.0, Unit.GRAM));
        model.addIngredient(new IngredientBuilder().build());
        expectedModel.addIngredient(new IngredientBuilder().build());
        expectedModel.useIngredient(new Name("Flour"), new Quantity(20.0, Unit.GRAM));
        assertCommandSuccess(command, model, message, expectedModel);
    }

    @Test
    public void execute_noQuantityParameter_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String message = "Ingredient used: Flour; \nQuantity Left: 0.0 GRAM\nIngredient Removed";
        UseCommand command = new UseCommand(new Name("Flour"), null);
        model.addIngredient(new IngredientBuilder().build());
        expectedModel.addIngredient(new IngredientBuilder().build());
        expectedModel.useIngredient(new Name("Flour"), new Quantity(50.0, Unit.GRAM));
        assertCommandSuccess(command, model, message, expectedModel);
    }

    @Test
    public void execute_invalidQuantityConversion_throwsCommandException() {
        Model model = new ModelManager();
        UseCommand command = new UseCommand(new Name("Flour"), new Quantity(1.0, Unit.PIECE));
        model.addIngredient(new IngredientBuilder().build());
        String expectedMessage = "Unit PIECE cannot be converted to GRAM!";
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        UseCommand first = new UseCommand(new Name("Flour"), new Quantity(50.0, Unit.GRAM));
        UseCommand second = new UseCommand(new Name("Egg"), new Quantity(50.0, Unit.GRAM));
        // Same command -> Returns true
        assertTrue(first.equals(first));
        // Same values -> Return true
        UseCommand firstCopy = new UseCommand(new Name("Flour"), new Quantity(50.0, Unit.GRAM));
        assertTrue(first.equals(firstCopy));
        // Different values -> Return false
        assertFalse(first.equals(second));
        // different types -> returns false
        assertFalse(first.equals(1));
    }

    @Test
    public void toStringMethod() {
        UseCommand useCommand = new UseCommand(new Name("Flour"), new Quantity(50.0, Unit.GRAM));
        String expected = UseCommand.class.getCanonicalName() + "{toUse=Flour}";
        assertEquals(expected, useCommand.toString());
    }
}
