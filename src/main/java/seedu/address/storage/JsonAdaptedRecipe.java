package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeStep;

/**
 * Jackson friendly version of {@link Recipe}.
 */
public class JsonAdaptedRecipe {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final int id;
    private final String name;
    private final List<JsonAdaptedIngredient> ingredients;
    private final List<String> steps;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given ingredient details.
     */
    public JsonAdaptedRecipe(@JsonProperty("id") int id, @JsonProperty("name") String name,
                             @JsonProperty("ingredients") List<JsonAdaptedIngredient> ingredients,
                             @JsonProperty("steps") List<String> steps) {

        requireAllNonNull(id, name, ingredients, steps);
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName().fullName;
        this.ingredients = recipe.getIngredients().stream().map(JsonAdaptedIngredient::new)
                .collect(Collectors.toList());
        this.steps = recipe.getRecipeSteps();
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        final List<Ingredient> modelIngredients = ingredients.stream().map(x -> {
            try {
                return x.toModelType();
            } catch (IllegalValueException e) {
                return null;
            }
        }).collect(Collectors.toList());

        final List<RecipeStep> modelSteps = steps.stream().map(RecipeStep::parseRecipeStep)
                .collect(Collectors.toList());

        return new Recipe(id, modelName, modelIngredients, modelSteps);
    }
}
