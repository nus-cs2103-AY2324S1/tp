package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHour(Integer)}
 */
public class Hour {

    public static final String MESSAGE_CONSTRAINTS =
            "Hour should only be between 0 to 9999, inclusive";
    public static final String EMPTY_HOUR = "0";
    public final Integer value;

    /**
     * Constructs a {@code Name}.
     *
     * @param hour A valid hour.
     */
    public Hour(Integer hour) {
        requireNonNull(hour);
        checkArgument(isValidHour(hour), MESSAGE_CONSTRAINTS);
        this.value = hour;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidHour(Integer test) {
        return test >= 0 && test <= 9999;
    }

    /**
     * @param duration It can be both negative and positive.
     * @return An updated Hour.
     */
    public Hour addHour(Integer duration) {
        requireNonNull(duration);
        Integer updatedValue = this.value + duration;
        checkArgument(isValidHour(updatedValue), MESSAGE_CONSTRAINTS);
        return new Hour(updatedValue);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Hour)) {
            return false;
        }

        Hour otherHour = (Hour) other;
        return value.equals(otherHour.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

