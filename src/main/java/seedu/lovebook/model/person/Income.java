package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents the date's income in the lovebook book.
 */
public class Income {

    public static final String MESSAGE_CONSTRAINTS = "Income can only take on positive values"
            + ", and it should not be blank";

    /*
     * The first character of the lovebook must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";

    public final String value;

    /**
     * Constructs an {@code Income}.
     *
     * @param income A valid income that is a positive integer.
     */
    public Income(String income) {
        requireNonNull(income);
        checkArgument(isValidIncome(income), MESSAGE_CONSTRAINTS);
        value = income;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidIncome(String test) {
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
        if (!(other instanceof Income)) {
            return false;
        }

        Income otherIncome = (Income) other;
        return value.equals(otherIncome.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
