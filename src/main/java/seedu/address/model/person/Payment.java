package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's payment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Payment {
    public static final String MESSAGE_CONSTRAINTS =
        "Salary should only contain numerical digits. It should not contain dashes or spaces."
            + "It should be in the format of XXXXX.XX, i.e. 2 decimal places";

    /*
     * The first character of the salary must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+\\.\\d{2}";

    public final String value;

    /**
     * Constructs a {@code Payment}.
     *
     * @param value A valid payment.
     */
    public Payment(String value) {
        requireNonNull(value);
        checkArgument(isValid(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid payment.
     */
    public static boolean isValid(String test) {
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
        if (!(other instanceof Payment)) {
            return false;
        }

        Payment otherPayment = (Payment) other;
        return value.equals(otherPayment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
