package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeStep;

/**
 * A utility class to help with building Recipe objects.
*/
public class RecipeBuilder {
    public static final String DEFAULT_RECIPE_NAME = "Cookies";
    private int id;
    private Name name;
    private List<Ingredient> ingredientList;
    private List<RecipeStep> recipeSteps;

    /** Creates a {@code RecipeBuilder} with the default details. */
    public RecipeBuilder() {
        this.name = new Name(DEFAULT_RECIPE_NAME);
        this.ingredientList = new ArrayList<>();
        this.recipeSteps = new ArrayList<>();
    }

    /** Sets the {@code name} of the {@code Recipe} we are building. */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /** Adds a {@code Ingredient} to the {@code Recipe} we are building. */
    public RecipeBuilder withIngredient(Ingredient ingredient) {
        this.ingredientList.add(ingredient);
        return this;
    }

    /** Adds a {@code RecipeStep} to the {@code Recipe} we are building. */
    public RecipeBuilder withSteps(String stepInstruction, int stepNumber) {
        this.recipeSteps.add(new RecipeStep(stepInstruction, stepNumber));
        return this;
    }

    /** Sets the {@code uniqueId} of the {@code Recipe} we are building. */
    public RecipeBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /** Builds the Recipe. */
    public Recipe build() {
        return new Recipe(this.id, this.name, this.ingredientList, this.recipeSteps);
    }
}
