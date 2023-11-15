package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalIngredients.FLOUR;
import static seedu.address.testutil.TypicalRecipe.COOKIES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.exceptions.RecipeStepFormatException;

public class JsonAdaptedRecipeTest {

    private static final String VALID_NAME = "Cookies";
    private static final String INVALID_NAME = "I @m not a Recipe N@me";

    @Test
    public void toModelType_validRecipeDetails_returnRecipe() throws Exception {
        JsonAdaptedRecipe jsonAdaptedRecipe = new JsonAdaptedRecipe(COOKIES);
        assertEquals(COOKIES, jsonAdaptedRecipe.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe invalidRecipe = new JsonAdaptedRecipe(1,
                INVALID_NAME, new ArrayList<>(), new ArrayList<>());
        assertThrows(IllegalValueException.class, () -> invalidRecipe.toModelType());
    }

    @Test
    public void toModelType_invalidRecipeSteps_throwsRecipeStepFormatException() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(FLOUR);

        List<String> stepsList = new ArrayList<>();
        stepsList.add("Not a valid step");

        JsonAdaptedRecipe invalidRecipe = new JsonAdaptedRecipe(1, VALID_NAME,
                ingredientList.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()), stepsList);
        assertThrows(RecipeStepFormatException.class, () -> invalidRecipe.toModelType());
    }

}
