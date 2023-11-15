package seedu.address.model.recipe.exceptions;

/**
 * Signals that the operation is unable to find the specified recipe.
 */
public class RecipeNotFoundException extends RuntimeException {
    private static String message = "Recipe does not exist in Recipe Book";
    public RecipeNotFoundException() {
        super(message);
    }

}
