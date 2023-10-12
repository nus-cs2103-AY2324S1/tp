package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Class Number
 * in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassNumber(String)}
 */
public class ClassNumber {

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";

    /*
     * The class number should start with "T".
     */
    public static final String VALIDATION_REGEX = "T.*";

    public final String value;

    /**
     * Constructs an {@code Class Number}.
     *
     * @param ClassNumber A valid Class Number
     *
     */
    public ClassNumber(String classNumber) {
        requireNonNull(classNumber);
        checkArgument(isValidClassNumber(classNumber), MESSAGE_CONSTRAINTS);
        value = classNumber;
    }

    /**
     * Returns true if a given string is a valid class number.
     */
    public static boolean isValidClassNumber(String test) {
        return (test.matches(VALIDATION_REGEX));


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
        if (!(other instanceof ClassNumber)) {
            return false;
        }

        ClassNumber otherAddress = (ClassNumber) other;
        return value.equals(otherAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
