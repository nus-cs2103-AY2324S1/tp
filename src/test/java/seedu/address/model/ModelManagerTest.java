package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.EGG;
import static seedu.address.testutil.TypicalIngredients.FLOUR;
import static seedu.address.testutil.TypicalRecipe.COOKIES;
import static seedu.address.testutil.TypicalRecipe.SPONGECAKE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ingredient.NameContainsKeywordsPredicate;
import seedu.address.testutil.InventoryBuilder;
import seedu.address.testutil.RecipeBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Inventory(), new Inventory(modelManager.getInventory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInventoryFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInventoryFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInventoryFilePath(null));
    }

    @Test
    public void setInventoryFilePath_validPath_setsInventoryFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInventoryFilePath(path);
        assertEquals(path, modelManager.getInventoryFilePath());
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIngredient(null));
    }

    @Test
    public void hasIngredient_ingredientNotInInventory_returnsFalse() {
        assertFalse(modelManager.hasIngredient(FLOUR.getName()));
    }

    @Test
    public void hasIngredient_ingredientInInventory_returnsTrue() {
        modelManager.addIngredient(FLOUR);
        assertTrue(modelManager.hasIngredient(FLOUR.getName()));
    }

    @Test
    public void getFilteredIngredientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredIngredientList().remove(0));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInList_returnsFalse() {
        assertFalse(modelManager.hasRecipe(COOKIES.getUuid()));
    }

    @Test
    public void hasRecipe_recipeInList_returnTrue() {
        modelManager.addRecipe(COOKIES);
        assertTrue(modelManager.hasRecipe(COOKIES.getUuid()));
    }

    //@Test
    //public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
    //    assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRecipeList().remove(0));
    //}
    //
    //@Test
    //public void getFilteredRecipeList_recipeInList_success() {
    //    modelManager.addRecipe(COOKIES);
    //    modelManager.addRecipe(SPONGECAKE);
    //    modelManager.updateFilteredRecipeList(x -> x.containsIngredient(new Name("Eggs")));
    //    assertEquals(1, modelManager.getFilteredRecipeList().size());
    //
    //    List<Recipe> expectedList = new ArrayList<>();
    //    expectedList.add(SPONGECAKE);
    //
    //    assertEquals(expectedList, new ArrayList<>(modelManager.getFilteredRecipeList()));
    //}

    @Test
    public void equals() {
        Inventory inventory = new InventoryBuilder().withIngredient(FLOUR).withIngredient(EGG).build();
        Inventory differentInventory = new Inventory();
        UserPrefs userPrefs = new UserPrefs();
        RecipeBook recipeBook = new RecipeBookBuilder().withRecipe(COOKIES).withRecipe(SPONGECAKE).build();
        RecipeBook differentRecipeBook = new RecipeBook();

        // same values -> returns true
        modelManager = new ModelManager(inventory, userPrefs, recipeBook);
        ModelManager modelManagerCopy = new ModelManager(inventory, userPrefs, recipeBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different inventory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInventory, userPrefs, recipeBook)));

        // different filteredList -> returns false
        String[] keywords = FLOUR.getName().fullName.split("\\s+");
        modelManager.updateFilteredIngredientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(inventory, userPrefs, recipeBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInventoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(inventory, differentUserPrefs, recipeBook)));

        // different recipeBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(inventory, userPrefs, differentRecipeBook)));

        // different filteredRecipeList -> returns false
        modelManager.updateFilteredRecipeList(x -> x.containsIngredient(new Name("Eggs")));
        assertFalse(modelManager.equals(new ModelManager(inventory, userPrefs, recipeBook)));
    }
}
