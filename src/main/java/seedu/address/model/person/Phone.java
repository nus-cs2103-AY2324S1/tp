package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns if a given string is a valid phone number.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected phone number format, false otherwise.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a string representation of this phone number.
     *
     * @return A string representation of this phone number.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this phone number.
     *
     * @param other The reference object with which to compare.
     * @return True if the other object is equal to this phone number, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    /**
     * Returns a hash code value for this phone number.
     *
     * @return A hash code value for this phone number.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
