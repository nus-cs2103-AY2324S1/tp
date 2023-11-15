package seedu.address.model.recipe;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Ingredient}'s {@code Name} matches any of the keywords given.
 */
public class RecipeUuidMatchesPredicate implements Predicate<Recipe> {
    private final UniqueId uuid;

    public RecipeUuidMatchesPredicate(UniqueId uuid) {
        this.uuid = uuid;
    }

    public UniqueId getUuid() {
        return this.uuid;
    }

    @Override
    public boolean test(Recipe recipe) {
        return this.uuid.equals(recipe.getUuid());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeUuidMatchesPredicate)) {
            return false;
        }

        RecipeUuidMatchesPredicate otherNameContainsKeywordsPredicate = (RecipeUuidMatchesPredicate) other;
        return this.uuid.equals(otherNameContainsKeywordsPredicate.uuid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("uuid", this.uuid).toString();
    }
}
