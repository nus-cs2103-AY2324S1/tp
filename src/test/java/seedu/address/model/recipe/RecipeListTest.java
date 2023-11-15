package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipe.COOKIES;
import static seedu.address.testutil.TypicalRecipe.SPONGECAKE;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.RecipeNotFoundException;

public class RecipeListTest {
    private final RecipeList recipeList = new RecipeList();

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeList.add(null));
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeList.remove(null));
    }

    @Test
    public void remove_existingRecipe_removesRecipe() {
        recipeList.add(COOKIES);
        recipeList.remove(COOKIES);
        RecipeList expectedRecipeList = new RecipeList();
        assertEquals(expectedRecipeList, recipeList);
    }

    @Test
    public void remove_nonExistingRecipe_throwsRecipeNotFoundException() {
        recipeList.add(COOKIES);
        assertThrows(RecipeNotFoundException.class, () -> recipeList.remove(SPONGECAKE));
    }

    @Test
    public void setRecipe_nullRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeList.setRecipes((RecipeList) null));
    }

    @Test
    public void setRecipe_recipeList_replacesOwnListWithProvidedRecipeList() {
        recipeList.add(COOKIES);
        RecipeList expectedRecipeList = new RecipeList();
        expectedRecipeList.add(SPONGECAKE);
        recipeList.setRecipes(expectedRecipeList);
        assertEquals(expectedRecipeList, recipeList);

    }

    @Test
    public void setRecipe_nullListOfRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeList.setRecipes((List<Recipe>) null));
    }

    @Test
    public void setRecipe_list_replacesOwnListWithProvidedList() {
        recipeList.add(COOKIES);
        List<Recipe> recipes = Collections.singletonList(SPONGECAKE);
        recipeList.setRecipes(recipes);
        RecipeList expectedRecipeList = new RecipeList();
        expectedRecipeList.add(SPONGECAKE);
        assertEquals(expectedRecipeList, recipeList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> recipeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(recipeList.asUnmodifiableObservableList().toString(), recipeList.toString());
    }
}
