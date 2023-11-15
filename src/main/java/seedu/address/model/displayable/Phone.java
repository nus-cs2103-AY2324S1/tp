package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a Displayable's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS = "Phone numbers must contain at least one number.";
    public static final String MESSAGE_RECOMMENDATIONS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long. "
                    + "Area codes are allowed, signified by a '+' and up to 3 numbers, followed by a space separating "
                    + "this from the main number.";
    public static final String VALIDATION_REGEX = ".*\\d.*";
    public static final String AFFIRMATION_REGEX = "((\\+\\d{0,3} )?)\\d{3,}";
    public static final String DEFAULT_PHONE_STRING = "123";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        AppUtil.validateArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isAppropriatePhone(String test) {
        return test.matches(AFFIRMATION_REGEX);
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
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
