package seedu.staffsnap.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Applicant's phone number in the applicant book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone implements Comparable<Phone> {


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

    /**
     * @param o the object to be compared.
     * @return the value 0 if the argument Phone is equal to this Phone; a value less than 0 if this Phone is
     *      lexicographically less than the Phone argument; and a value greater than 0 if this Phone is
     *      lexicographically greater than the Phone argument.
     */
    @Override
    public int compareTo(Phone o) {
        return this.value.compareTo(o.value);
    }
}
