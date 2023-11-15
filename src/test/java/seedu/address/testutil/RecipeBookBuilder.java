package seedu.address.testutil;

import seedu.address.model.RecipeBook;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class to help with building RecipeBook objects.
 */
public class RecipeBookBuilder {

    private RecipeBook recipeBook;

    /** Creates a {@code RecipeBookBuilder} with the default details. */
    public RecipeBookBuilder() {
        this.recipeBook = new RecipeBook();
    }

    /** Creates a {@code RecipeBookBuilder} with the given {@code recipeBook} details. */
    public RecipeBookBuilder(RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }

    /** Adds a new {@code Recipe} to the {@code RecipeBook} */
    public RecipeBookBuilder withRecipe(Recipe recipe) {
        this.recipeBook.addRecipe(recipe);
        return this;
    }

    public RecipeBook build() {
        return this.recipeBook;
    }
}
