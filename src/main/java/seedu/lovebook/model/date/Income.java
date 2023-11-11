package seedu.lovebook.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents the date's income in the LoveBook.
 */
public class Income implements Comparable<Income> {

    public static final String MESSAGE_CONSTRAINTS = "Income can only take on positive integer values, "
            + "less than or equal"
            + " to a million"
            + ", and it should not be blank";

    /*
     * The first character of the LoveBook must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^([1-9]\\d{0,5}|[1-9]\\d{0,5}|1000000)$";

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
     * Returns true if a given string is a valid income
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

    @Override
    public int compareTo(Income o) {
        long thisIncome = Long.parseLong(this.toString());
        long otherIncome = Long.parseLong(o.toString());
        if (thisIncome > otherIncome) {
            return 1;
        } else if (thisIncome < otherIncome) {
            return -1;
        }
        return 0;
    }
}
