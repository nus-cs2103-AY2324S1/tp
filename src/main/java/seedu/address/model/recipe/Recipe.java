package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;

/**
 * Represents a recipe in the recipe book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    private final UniqueId uuid;
    private final Name name;
    private final List<Ingredient> ingredientList;
    private final List<RecipeStep> recipeSteps;

    /**
     * Creates a new Recipe.
     */
    public Recipe(Name name, List<Ingredient> ingredientList, List<RecipeStep> recipeSteps) {
        requireAllNonNull(name, ingredientList, recipeSteps);
        this.uuid = new UniqueId();
        this.name = name;
        this.ingredientList = ingredientList;
        this.recipeSteps = recipeSteps;
    }

    /** Creates a new Recipe with the specified {@code id}. */
    public Recipe(int id, Name name, List<Ingredient> ingredientList, List<RecipeStep> recipeSteps) {
        requireAllNonNull(id, name, ingredientList, recipeSteps);
        this.uuid = UniqueId.importUniqueId(id);
        this.name = name;
        this.ingredientList = ingredientList;
        this.recipeSteps = recipeSteps;
    }

    public Name getName() {
        return this.name;
    }

    public int getId() {
        return this.uuid.getId();
    }

    public UniqueId getUuid() {
        return this.uuid;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredientList;
    }

    public List<String> getRecipeSteps() {
        return this.recipeSteps.stream().map(RecipeStep::toString).collect(Collectors.toList());
    }

    /** Check if the current recipe contains the specified ingredient name. */
    public boolean containsIngredient(Name ingredientName) {
        requireNonNull(ingredientName);
        return ingredientList.stream().anyMatch(x -> x.getName().equals(ingredientName));
    }

    /**
     * Changes the specified recipe step with the new instructions.
     */
    public Recipe modifyRecipeStep(int stepNumber, String newStep) {
        requireNonNull(newStep);
        if (stepNumber > recipeSteps.size()) {
            throw new IllegalArgumentException("Specified step number cannot exceed the current steps of recipe");
        }
        List<RecipeStep> stepCopy = new ArrayList<>(this.recipeSteps);
        stepCopy.set(stepNumber - 1, stepCopy.get(stepNumber - 1).modifyStep(newStep));
        return new Recipe(this.uuid.getId(), this.name, this.ingredientList, stepCopy);
    }

    /**
     * Changes the specified recipe step with a new step number.
     */
    public Recipe modifyRecipeStep(int stepNumber, int newStepNumber) {
        if (stepNumber > recipeSteps.size()) {
            throw new IllegalArgumentException("Specified step number cannot exceed the current steps of recipe");
        }
        List<RecipeStep> stepCopy = new ArrayList<>(this.recipeSteps);
        stepCopy.set(stepNumber - 1, stepCopy.get(stepNumber - 1).modifyStep(newStepNumber));
        return new Recipe(this.uuid.getId(), this.name, this.ingredientList, stepCopy);
    }

    /**
     * Changes the specified ingredient with a new ingredient.
     */
    public Recipe modifyIngredients(String oldIngredient, Ingredient newIngredient) {
        requireAllNonNull(oldIngredient, newIngredient);
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getName().equals(new Name(oldIngredient))) {
                List<Ingredient> ingredientListCopy = new ArrayList<>(ingredientList);
                ingredientListCopy.remove(ingredient);
                ingredientListCopy.add(newIngredient);
                return new Recipe(this.uuid.getId(), this.name, ingredientListCopy, this.recipeSteps);
            }
        }
        throw new IngredientNotFoundException();
    }

    /**
     * Adds ingredient to ingredient list in a recipe
     * @param ingredient ingredient that will be added
     * @return the edited Recipe with the added ingredient
     */
    public Recipe addIngredient(Ingredient ingredient) {
        List<Ingredient> ingredientListCopy = new ArrayList<>(ingredientList);
        ingredientListCopy.add(ingredient);
        return new Recipe(this.uuid.getId(), this.name, ingredientListCopy, this.recipeSteps);
    }

    /**
     * Returns the full recipe.
     */
    public String getFullRecipe() {
        StringBuilder ingredients = new StringBuilder();
        for (Ingredient ingredient : ingredientList) {
            ingredients.append(ingredient.getName()).append(" ").append(ingredient.getQuantity()).append("\n");
        }
        StringBuilder steps = new StringBuilder();
        for (RecipeStep recipeStep : recipeSteps) {
            steps.append(recipeStep.toString()).append("\n");
        }
        return String.format("%d. %s\n%s%s", this.getId(), this.name, ingredients, steps.toString().stripTrailing());
    }

    /**
     * Returns the ingredients needed in textual format.
     */
    public String getIngredientsText() {
        StringBuilder ingredients = new StringBuilder();
        int counter = 1;
        for (Ingredient ingredient : ingredientList) {
            //String str = String.valueOf(70 - ingredient.getName().toString().length());
            // Should find a way to align in javafx as alignment in javafx and terminal is different
            String str = "";
            ingredients.append(String.format("%d. %s %" + str + "s\n",
                    counter, ingredient.getName(), ingredient.getQuantity()));
            counter++;
        }
        return ingredients.toString();
    }

    /**
     * Returns the steps needed in textual format.
     */
    public String getStepsText() {
        StringBuilder steps = new StringBuilder();
        for (RecipeStep recipeStep : recipeSteps) {
            steps.append(recipeStep.toString()).append("\n");
        }
        return steps.toString();
    }

    @Override
    public String toString() {
        return this.name.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return this.uuid.getId() == otherRecipe.uuid.getId()
                && this.name.equals(otherRecipe.name)
                && this.ingredientList.equals(otherRecipe.ingredientList)
                && this.recipeSteps.equals(otherRecipe.recipeSteps);
    }

}
