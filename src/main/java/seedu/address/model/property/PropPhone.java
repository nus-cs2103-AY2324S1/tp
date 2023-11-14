package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class PropPhone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should start with 6, 8 or 9, and it should be 8 digits long";
    public static final String VALIDATION_REGEX = "(6|8|9)\\d{7}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public PropPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PropPhone)) {
            return false;
        }

        PropPhone otherPropPhone = (PropPhone) other;
        return value.equals(otherPropPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
