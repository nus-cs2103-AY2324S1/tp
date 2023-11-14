package seedu.spendnsplit.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.spendnsplit.commons.util.AppUtil.checkArgument;

import org.apache.commons.numbers.fraction.BigFraction;

import seedu.spendnsplit.commons.util.FractionUtil;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;

/**
 * Represents a Transaction's amount.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String VALUE_CONSTRAINT = "Amount should be greater than 0";
    public static final String MESSAGE_CONSTRAINTS =
        "Amount should be a valid decimal number, and it should not be blank";
    public static final String VALIDATION_REGEX =
            "^-?(([0-9 ]*[0-9][0-9 ]*)|([0-9 ]*\\.[0-9 ]*))(/(([0-9 ]*[0-9][0-9 ]*)|([0-9 ]*\\.[0-9 ]*)))?$";
    public static final int DEFAULT_DECIMAL_PLACES = 2;

    // Identity fields
    public final BigFraction amount;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid amount.
     */
    public Amount(String amount) throws ParseException {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = FractionUtil.parseFraction(amount);
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return FractionUtil.toString(amount, DEFAULT_DECIMAL_PLACES);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) other;
        return amount.equals(otherAmount.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
