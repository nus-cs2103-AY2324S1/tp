package seedu.address.model.customer;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.property.Price;

/**
 * Represents a Customer's budget in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBudget(String)}
 */
public class Budget {


    public static final String MESSAGE_CONSTRAINTS =
            "Budget should be \n"
                    + "- an integer\n"
                    + "- at least 10000\n"
                    + "- less than 1 trillion (1 000 000 000 000)\n"
                    + "- not start with 0\n";
    public static final String VALIDATION_REGEX = "[1-9]\\d{4,11}";
    public final Long amount;
    public final String value;

    /**
     * Constructs a {@code Budget}.
     *
     * @param budget A valid budget number.
     */
    public Budget(String budget) {
        requireNonNull(budget);
        checkArgument(isValidBudget(budget), MESSAGE_CONSTRAINTS);
        amount = Long.parseUnsignedLong(budget);
        value = budget;
    }

    /**
     * Returns true if a given string is a valid budget.
     */
    public static boolean isValidBudget(String test) {
        return test.matches(VALIDATION_REGEX)
                && Long.parseUnsignedLong(test) >= 10000
                && Long.parseUnsignedLong(test) < Long.parseUnsignedLong("1000000000000");
    }

    /**
     * Returns true if the other budget is greater or equal this budget.
     *
     * @param other the other budget being compared
     * @return whether the other budget is greater or equal to this budget
     */
    public boolean isInRangeBudget(Budget other) {
        return isNull(other) || amount >= other.amount;
    }

    /**
     * Convert the budget to a price object.
     *
     * @return a Price object that have the same amount with the budget.
     */
    public Price convertToPrice() {
        return new Price(value);
    }
    @Override
    public String toString() {
        return "$" + value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return value.equals(otherBudget.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
