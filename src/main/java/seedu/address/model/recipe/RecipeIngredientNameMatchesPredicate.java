package seedu.address.model.recipe;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.ingredient.Ingredient;

/**
 * Tests that a {@code Ingredient}'s {@code Name} matches any of the keywords given.
 */
public class RecipeIngredientNameMatchesPredicate implements Predicate<Recipe> {
    private final String name;

    public RecipeIngredientNameMatchesPredicate(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Recipe recipe) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient.getName().toString().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeIngredientNameMatchesPredicate)) {
            return false;
        }

        RecipeIngredientNameMatchesPredicate otherNameContainsKeywordsPredicate =
                (RecipeIngredientNameMatchesPredicate) other;
        return this.name.equals(otherNameContainsKeywordsPredicate.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).toString();
    }
}
