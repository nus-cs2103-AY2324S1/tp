package seedu.address.testutil;

import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;

/**
 * A utility class to help with building Ingredient objects.
 */
public class IngredientBuilder {

    public static final String DEFAULT_NAME = "Flour";
    private Name name;
    private Quantity quantity;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public IngredientBuilder() {
        name = new Name(DEFAULT_NAME);
        quantity = new Quantity(50, Unit.GRAM);
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public IngredientBuilder(Ingredient ingredientToCopy) {
        name = ingredientToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Ingredient} that we are building
     * @param val The value of the quantity
     * @param unit The unit of th quantity
     */
    public IngredientBuilder withQuantity(double val, Unit unit) {
        this.quantity = new Quantity(val, unit);
        return this;
    }

    // TODO Add JavaDocs
    /**
     * Stub
     * @return Stub
     */
    public Ingredient build() {
        return new Ingredient(name, quantity);
    }

}
