package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's availability to foster an animal in the address book.
 */
public class Availability {
    public static final String MESSAGE_CONSTRAINTS = "Availability should be either 'Available', "
            + "'NotAvailable' or 'nil'";

    public static final String VALIDATION_REGEX = "^(Available|NotAvailable|nil)$";

    public static final String AVAILABLE_WORD = "Available";
    public static final String NOT_AVAILABLE_WORD = "NotAvailable";
    public static final Availability AVAILABLE = new Availability(AVAILABLE_WORD);
    public static final Availability NOT_AVAILABLE = new Availability(NOT_AVAILABLE_WORD);
    public static final Availability NIL_AVAILABILITY = new Availability(Person.NIL_WORD);

    public final String value;

    /**
     * Constructs an {@code Availability}.
     *
     * @param value A valid availability.
     */
    public Availability(String value) {
        requireNonNull(value);
        checkArgument(isValidAvailability(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Availability otherAvailability = (Availability) other;
        return value.equals(otherAvailability.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
