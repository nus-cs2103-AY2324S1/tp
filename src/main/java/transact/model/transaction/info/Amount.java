package transact.model.transaction.info;

import transact.logic.parser.exceptions.ParseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Represents an amount of money in the system.
 * Guarantees: immutable; amount is non-null and non-negative.
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount must be non-null and non-negative.";
    private static final NumberFormat AMOUNT_FORMAT = new DecimalFormat("#0.00");

    private final BigDecimal value;

    /**
     * Constructs an {@code Amount}.
     *
     * @param amount
     *            A non-null, non-negative amount of money.
     */
    public Amount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = amount.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Constructs an {@code Amount} with a double value.
     *
     * @param amount
     *            A non-negative amount of money as a double.
     */
    public Amount(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    /**
     * Returns the amount as a BigDecimal.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Returns the amount as a formatted string.
     */
    public String getFormattedValue() {
        return AMOUNT_FORMAT.format(value);
    }

    @Override
    public String toString() {
        return getFormattedValue();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Amount)) {
            return false;
        }
        Amount otherAmount = (Amount) other;
        return value.equals(otherAmount.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String amount) {
        try {
            double amt = Double.parseDouble(amount);
            return amt > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
