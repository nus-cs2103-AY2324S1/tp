package seedu.address.model.finance;

import static java.util.Objects.requireNonNull;
/**
 * Represents an Amount received in a Commission or spent in an Expense.
 */
public class Amount {
    public static final String VALIDATION_REGEX = "^[1-9]\\d*(\\.\\d{1,2})?|0\\.\\d{1,2}$\n";
    public static final String MESSAGE_CONSTRAINTS =
            "Amounts should be strictly positive and up to 2dp";
    public final String value;
    /**
     * Constructs an Amount object with the specified amount value.
     *
     * @param amount The amount value to be associated with this object.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        this.value = amount;
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
        return this.value.equals(otherAmount.value);
    }
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
