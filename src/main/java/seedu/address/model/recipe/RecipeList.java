package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;

/**
 * A list of recipe that does not allow nulls.
 */
public class RecipeList implements Iterable<Recipe> {
    private final ObservableList<Recipe> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /** Adds a new {@code Recipe} to the list. */
    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /** Removes a {@code Recipe} from the list. */
    public void remove(Recipe toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RecipeNotFoundException();
        }
    }

    /**
     * Empties the list.
     */
    public void clear() {
        internalList.clear();
    }

    /** Returns an immutable list for JavaFX. */
    public ObservableList<Recipe> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /** Replaces the current recipe list with the {@code replacement} list. */
    public void setRecipes(RecipeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     */
    public void setRecipes(List<Recipe> recipeList) {
        requireAllNonNull(recipeList);
        internalList.setAll(recipeList);
    }

    /**
     * Returns the full recipe representation of the specified {@code recipeId}.
     * @throws RecipeNotFoundException if recipe does not exist.
     */
    public String getFullRecipe(int recipeId) {
        return this.internalList.stream().filter(x -> x.getId() == recipeId).findFirst()
                .orElseThrow(RecipeNotFoundException::new).getFullRecipe();
    }

    @Override
    public Iterator<Recipe> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeList)) {
            return false;
        }

        RecipeList otherRecipeList = (RecipeList) other;
        return internalList.equals(otherRecipeList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
