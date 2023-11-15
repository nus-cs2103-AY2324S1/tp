package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent an item's price.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {
    public static final String MESSAGE_CONSTRAINTS =
        "Price should be a non-negative number with 2 decimal places. "
                + "Remove leading 0s if the price is 1.00 and above. "
                + "Only have 1 leading 0 if the price is less than 1.00, eg. 0.50";

    public static final String VALIDATION_REGEX = "^(0\\.\\d{2}|[1-9]\\d*\\.\\d{2})$";

    public final String price;
    public final double priceDouble;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid name.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = price;
        this.priceDouble = Double.parseDouble(price);
        assert priceDouble >= 0;
    }

    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Price // instanceof handles nulls
            && price.equals(((Price) other).price)); // state check
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }

}
