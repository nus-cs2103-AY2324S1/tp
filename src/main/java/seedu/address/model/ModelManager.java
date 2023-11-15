package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueId;

/**
 * Represents the in-memory model of the inventory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final RecipeBook recipeBook;
    private final FilteredList<Ingredient> filteredIngredients;
    private final FilteredList<Recipe> filteredRecipe;

    /**
     * Initializes a ModelManager with the given inventory and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs, ReadOnlyRecipeBook recipeBook) {
        requireAllNonNull(inventory, userPrefs, recipeBook);

        logger.fine("Initializing with inventory: " + inventory + " and user prefs " + userPrefs);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        this.recipeBook = new RecipeBook(recipeBook);
        filteredIngredients = new FilteredList<>(this.inventory.getIngredientList());
        filteredRecipe = new FilteredList<>(this.recipeBook.getRecipeList());
    }

    public ModelManager() {
        this(new Inventory(), new UserPrefs(), new RecipeBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInventoryFilePath() {
        return userPrefs.getInventoryFilePath();
    }

    @Override
    public void setInventoryFilePath(Path inventoryFilePath) {
        requireNonNull(inventoryFilePath);
        userPrefs.setInventoryFilePath(inventoryFilePath);
    }

    //=========== StockBook ================================================================================

    @Override
    public void setInventory(ReadOnlyInventory stock) {
        this.inventory.resetData(stock);
    }

    @Override
    public ReadOnlyInventory getInventory() {
        return inventory;
    }

    @Override
    public boolean hasIngredient(Name ingredientName) {
        requireNonNull(ingredientName);

        return inventory.hasIngredient(ingredientName);
    }

    @Override
    public Quantity getQuantityOf(Name ingredientName) {
        requireNonNull(ingredientName);
        return inventory.getQuantityOf(ingredientName);
    }

    @Override
    public void deleteIngredient(Name ingredientName) {
        inventory.removeIngredient(ingredientName);
    }

    @Override
    public void deleteIngredients() {
        inventory.clear();
    }
    @Override
    public void addIngredient(Ingredient ingredient) {
        inventory.addIngredient(ingredient);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    @Override
    public void useIngredient(Name ingredientName, Quantity quantity) {
        requireAllNonNull(ingredientName, quantity);
        inventory.useIngredient(ingredientName, quantity);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    //=========== RecipeBook =============================================================

    @Override
    public void setRecipeBook(ReadOnlyRecipeBook recipeBook) {
        requireNonNull(recipeBook);
        this.recipeBook.resetData(recipeBook);
    }

    @Override
    public ReadOnlyRecipeBook getRecipeBook() {
        return this.recipeBook;
    }

    @Override
    public boolean hasRecipe(UniqueId uuid) {
        requireNonNull(uuid);
        return recipeBook.hasRecipe(uuid);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        this.recipeBook.removeRecipe(recipe);
    }
    @Override
    public void addRecipe(Recipe recipe) {
        requireNonNull(recipe);
        this.recipeBook.addRecipe(recipe);
    }

    @Override
    public Recipe getRecipe(UniqueId uuid) {
        return this.recipeBook.getRecipe(uuid);
    }

    @Override
    public String getFullRecipe(int recipeId) {
        return this.recipeBook.getFullRecipe(recipeId);
    }

    //=========== Filtered Ingredient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedInventory}
     */
    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return filteredRecipe;
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        requireNonNull(predicate);
        this.filteredRecipe.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return inventory.equals(otherModelManager.inventory)
                && userPrefs.equals(otherModelManager.userPrefs)
                && recipeBook.equals(otherModelManager.recipeBook)
                && filteredIngredients.equals(otherModelManager.filteredIngredients)
                && filteredRecipe.equals(otherModelManager.filteredRecipe);
    }

}
