package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Optional;

/**
 * A class representing salary
 */
public class Pay {
    public static final String MESSAGE_CONSTRAINTS = "Pay must be a non-negative number";

    // Matches positive numbers, any dp. Inputs cannot start with a period
    private static final String VALID_PAY_REGEX = "^[+]?\\d+([.]\\d+)?$";

    private final BigDecimal pay; // This can be null, use this.getPay() instead of referencing it directly!

    private final NumberFormat moneyFormatter;

    /**
     * Creates a pay object
     * @param pay the salary amount
     */
    public Pay(BigDecimal pay) {
        this.pay = pay;

        moneyFormatter = NumberFormat.getCurrencyInstance();
        moneyFormatter.setRoundingMode(RoundingMode.DOWN);
        moneyFormatter.setMinimumFractionDigits(2);
        moneyFormatter.setMaximumFractionDigits(2);
    }

    /**
     * @param pay to be verified
     * @return true if input is a positive number and does not start with a period
     */
    public static boolean isValidPay(String pay) {
        return pay.matches(VALID_PAY_REGEX);
    }

    /**
     * Verify if a pay is valid
     * @param pay to be verified
     * @return true if pay is non-negative
     */
    public static boolean isValidPay(BigDecimal pay) {
        requireNonNull(pay);
        return pay.compareTo(new BigDecimal(0)) > -1;
    }

    public Optional<BigDecimal> getPay() {
        return Optional.ofNullable(pay);
    }

    /**
     * @return a string in "money" representation with commas and unit
     */
    @Override
    public String toString() {
        return this.getPay().map(moneyFormatter::format).orElse("");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Pay)) {
            return false;
        }

        Pay otherPay = (Pay) other;
        return this.getPay().equals(otherPay.getPay());
    }

    @Override
    public int hashCode() {
        return this.getPay().hashCode();
    }

}
