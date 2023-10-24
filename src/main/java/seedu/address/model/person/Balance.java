package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's payment balance in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isWithinLimits(Integer)}
 */
public class Balance {

    /**
     * Message constraints for Balance when represented as a dollar string,
     * which is the format used for user input.
     */
    public static final String MESSAGE_CONSTRAINTS = "Balance should be a valid dollar amount, "
            + "with maximum precision of 2 d.p. / (cents).\n"
            + "A prepended dollar sign is optional, and dollar amount must be non-negative.\n"
            + "Examples: 2.55, $55.1, $5, $0.01, 0.09, 0";

    public static final String MESSAGE_BALANCE_LIMIT_EXCEEDED = "The maximum amount that can be "
            + "owed to any contact in CampusConnect is $10,000.\n"
            + "CampusConnect should only be used for casual transactions among friends.";

    public static final int MAX_VALUE = 1000000;

    public static final int MIN_VALUE = -1000000;

    /**
     * Regex for Balance when represented as a dollar string,
     */
    public static final String VALIDATION_REGEX = "^\\$?\\d+(\\.\\d{1,2})?$";

    /**
     * The balance in cents.
     * <p>
     * An integer representing cents is used instead of a double representing dollars
     * to avoid floating point precision issues, which is crucial since this is responsible
     * for tracking financial transactions.
     */
    public final Integer value;

    /**
     * Constructs a {@code Balance}.
     *
     * @param balanceInCents A valid balance in cents.
     */
    public Balance(Integer balanceInCents) {
        requireNonNull(balanceInCents);
        checkArgument(balanceInCents <= MAX_VALUE && balanceInCents >= MIN_VALUE,
                MESSAGE_BALANCE_LIMIT_EXCEEDED);
        value = balanceInCents;
    }

    /**
     * Returns true if a given integer is within the balance limit.
     */
    public static boolean isWithinLimits(Integer balance) {
        return balance <= MAX_VALUE && balance >= MIN_VALUE;
    }

    /**
     * Returns true if a given string is a valid dollar string
     * which can be parsed into a cents amount.
     */
    public static boolean isValidDollarString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a dollar string representation of the balance.
     */
    public String toDollarString() {
        return String.format("$%d.%02d", value / 100, value % 100);
    }

    /**
     * Returns a full message explaining the status of a Person's
     * balance with another Person, to be shown in a GUI.
     */
    public String toUiMessage() {
        if (value < 0) {
            return String.format("You owe them: %s", toDollarString());
        }
        return String.format("They owe you: %s", toDollarString());
    }

    /**
     * Adds the given balance to this balance.
     * @param other the balance to be added.
     * @return a new Balance object representing the sum of the two balances.
     */
    public Balance add(Balance other) {
        if (other == null) {
            return this;
        }
        return new Balance(this.value + other.value);
    }

    /**
     * Returns true if the person would exceed the balance limit
     * after adding the given amount.
     * @param other the balance to be added.
     * @return true if the balance would exceed the limit once the given amount is added.
     */
    public boolean wouldExceedBalanceLimit(Balance other) {
        return this.add(other).value > MAX_VALUE
                || this.add(other).value < MIN_VALUE;
    }



    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Balance)) {
            return false;
        }

        Balance otherBalance = (Balance) other;
        return value.equals(otherBalance.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
