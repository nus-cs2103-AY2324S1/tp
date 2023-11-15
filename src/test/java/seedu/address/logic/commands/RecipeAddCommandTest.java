package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RecipeBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueId;
import seedu.address.testutil.RecipeBuilder;

public class RecipeAddCommandTest {
    @Test
    public void execute_recipeAcceptedByModel_addRecipeSuccessful() throws Exception {
        ModelStubAcceptingRecipeAdded
            modelStub = new ModelStubAcceptingRecipeAdded();
        Recipe validRecipe = new RecipeBuilder().build();

        CommandResult commandResult = new RecipeAddCommand(validRecipe).execute(modelStub);

        assertEquals(String.format(RecipeAddCommand.MESSAGE_SUCCESS, Messages.format(validRecipe)),
            commandResult.getFeedbackToUser());
        assertEquals(List.of(validRecipe), modelStub.recipeBook.getRecipeList());
    }

    @Test
    public void constructor_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeAddCommand(null));
    }

    @Test
    public void equals() {
        Recipe a = new RecipeBuilder().withName("cookie").build();
        RecipeAddCommand adda = new RecipeAddCommand(a);
        RecipeAddCommand addb = new RecipeAddCommand(a);
        // same object -> returns true
        assertEquals(adda, adda);

        // same values -> returns true
        assertEquals(adda, addb);

        // different types -> returns false
        assertNotEquals(1, adda);

        // null -> returns false
        assertNotEquals(null, adda);

        Recipe b = new RecipeBuilder().withName("cake").build();
        RecipeAddCommand addc = new RecipeAddCommand(b);
        // different name -> returns false
        assertNotEquals(adda, addc);
    }

    @Test
    public void toStringMethod() {
        Recipe a = new RecipeBuilder().withName("cookie").build();
        RecipeAddCommand recipeAddCommand = new RecipeAddCommand(a);
        String expected = RecipeAddCommand.class.getCanonicalName() + "{toAdd=" + a + "}";
        assertEquals(expected, recipeAddCommand.toString());
    }


    /**
     * A default model stub that has all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInventoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInventoryFilePath(Path inventoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInventory(ReadOnlyInventory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInventory getInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIngredient(Name ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIngredient(Name target) {
            throw new AssertionError("This method should not be called.");
        }

        //  @Override
        //  public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        //      throw new AssertionError("This method should not be called.");
        //  }

        @Override
        public ObservableList<Ingredient> getFilteredIngredientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Quantity getQuantityOf(Name ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIngredients() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void useIngredient(Name name, Quantity quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipeBook(ReadOnlyRecipeBook recipeBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRecipeBook getRecipeBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecipe(UniqueId uuid) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getFullRecipe(int recipeId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Recipe getRecipe(UniqueId uuid) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the recipe being added.
     */
    private static class ModelStubAcceptingRecipeAdded extends ModelStub {
        final RecipeBook recipeBook = new RecipeBook();
        @Override
        public void addRecipe(Recipe recipe) {
            requireNonNull(recipe);
            this.recipeBook.addRecipe(recipe);
        }
    }

}

