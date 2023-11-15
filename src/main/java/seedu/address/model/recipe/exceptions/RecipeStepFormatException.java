package seedu.address.model.recipe.exceptions;

/**
 * Signals that the operation is unable to parse the {@link seedu.address.model.recipe.RecipeStep}.
 */
public class RecipeStepFormatException extends IllegalArgumentException {
    private static String template = "Not a valid recipe step!";
    public RecipeStepFormatException() {
        super(template);
    }
}
