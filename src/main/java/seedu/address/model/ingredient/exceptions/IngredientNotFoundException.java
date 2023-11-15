package seedu.address.model.ingredient.exceptions;

/**
 * Signals that the operation is unable to find the specified ingredient.
 */
public class IngredientNotFoundException extends RuntimeException {
    private static String message = "Ingredient does not exist in Inventory ";
    public IngredientNotFoundException() {
        super(message);
    }
}
