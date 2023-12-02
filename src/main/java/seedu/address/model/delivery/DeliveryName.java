package seedu.address.model.delivery;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's name in HomeBoss.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class DeliveryName implements Comparable<DeliveryName> {

    public static final String MESSAGE_CONSTRAINTS =
        "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String deliveryName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public DeliveryName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.deliveryName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return deliveryName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryName)) {
            return false;
        }

        DeliveryName otherName = (DeliveryName) other;
        return deliveryName.equals(otherName.deliveryName);
    }

    @Override
    public int hashCode() {
        return deliveryName.hashCode();
    }

    @Override
    public int compareTo(DeliveryName o) {
        return this.deliveryName.compareTo(o.deliveryName);
    }
}
