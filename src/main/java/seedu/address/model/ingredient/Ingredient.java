package seedu.address.model.ingredient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Ingredient {

    // Identity fields
    private final Name name;
    private Quantity quantity;

    /**
     * Every field must be present and not null.
     */
    public Ingredient(Name name, Quantity quantity) {
        // requireAllNonNull(name, phone, email, address, tags);
        requireAllNonNull(name);
        this.name = name;
        this.quantity = quantity;
        /*
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        */
    }

    /**
     * Get the name of this ingredient.
     * @return
     */
    public Name getName() {
        return name;
    }

    /**
     * Get the quantity of this ingredient.
     * @return
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Combines another ingredient of the same type with this ingredient, combining their quantities .
     * @param otherIngredient
     */
    protected void combineWith(Ingredient otherIngredient) {
        //otherIngredient is guaranteed to be of the same type as this ingredient.
        this.quantity.add(otherIngredient.quantity);
    }

    /**
     * Returns true if both ingredients have the same name.
     * This defines a weaker notion of equality between two ingredients.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two ingredients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return name.equals(otherIngredient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("quantity", quantity)
                .toString();
    }

    public void use(Quantity quantity) {
        this.quantity.subtract(quantity);
    }
}
