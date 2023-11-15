package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecipe.COOKIES;
import static seedu.address.testutil.TypicalRecipe.COOKIES_STRING;
import static seedu.address.testutil.TypicalRecipe.SPONGECAKE;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;

public class RecipeBookTest {

    private final RecipeBook recipeBook = new RecipeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), recipeBook.getRecipeList());
    }

    @Test
    public void resetData_nullData_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        RecipeBook typicalRecipeBook = getTypicalRecipeBook();
        recipeBook.resetData(typicalRecipeBook);
        assertEquals(typicalRecipeBook, recipeBook);
    }

    @Test
    public void hasRecipe_nullRecipeName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(COOKIES);
        assertTrue(recipeBook.hasRecipe(COOKIES.getUuid()));
    }

    @Test
    public void hasRecipe_recipeNotInRecipeBook_returnsFalse() {
        recipeBook.addRecipe(COOKIES);
        assertFalse(recipeBook.hasRecipe(SPONGECAKE.getUuid()));
    }

    @Test
    public void clear_recipeInRecipeBook_returnsEmptyList() {
        recipeBook.addRecipe(COOKIES);
        recipeBook.addRecipe(SPONGECAKE);
        ObservableList tmpList = recipeBook.getRecipeList();
        assertEquals(tmpList.size(), 2);
        recipeBook.clear();
        assertEquals(tmpList.size(), 0);
    }

    @Test
    public void getFullRecipeMethod() {
        recipeBook.addRecipe(COOKIES);
        assertEquals(COOKIES_STRING, recipeBook.getFullRecipe(COOKIES.getId()));
    }

    @Test
    public void removeRecipe_recipeInRecipeBook_success() {
        recipeBook.addRecipe(COOKIES);
        recipeBook.removeRecipe(COOKIES);
        assertEquals(new RecipeBook(), recipeBook);
    }

    @Test
    public void removeRecipe_recipeNotInRecipeBook_throwsRecipeNotFoundException() {
        recipeBook.addRecipe(COOKIES);
        assertThrows(RecipeNotFoundException.class, () -> recipeBook.removeRecipe(SPONGECAKE));
    }
}
