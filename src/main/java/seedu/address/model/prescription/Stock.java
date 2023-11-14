package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Prescription's stock in the prescription list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStockFormat(String)}
 */
public class Stock {

    public static final String MESSAGE_CONSTRAINTS =
            "Stocks should only contain numeric characters, and it should not be blank.";
    public static final String MESSAGE_LARGE_STOCK =
            "The stock value inputted is too large. Please input a smaller stock value.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    private String fullStock;

    /**
     * Constructs a {@code Stock}.
     *
     * @param stock A valid stock.
     */
    public Stock(String stock) {
        requireNonNull(stock);
        checkArgument(isValidStockFormat(stock), MESSAGE_CONSTRAINTS);
        fullStock = stock;
    }

    /**
     * Returns true if a given string is a valid stock.
     */
    public static boolean isValidStockFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid stock.
     */
    public static boolean isValidStock(String test) {
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void setFullStock(String fullStock) {
        this.fullStock = fullStock;
    }

    /**
     * increment the count of stock.
     * @param count The count to increment by.
     */
    public void incrementCount(int count) {
        int newStock = Integer.parseInt(this.fullStock) + count;
        this.fullStock = Integer.toString(newStock);
    }

    /**
     * Decrements the count of stock.
     * @param count The count to decrement by.
     */
    public void decrementCount(int count) {
        int newStock = Integer.parseInt(this.fullStock) - count;
        this.fullStock = Integer.toString(newStock);
    }

    @Override
    public String toString() {
        return fullStock;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stock)) {
            return false;
        }

        Stock otherStock = (Stock) other;
        return fullStock.equals(otherStock.fullStock);
    }

    @Override
    public int hashCode() {
        return fullStock.hashCode();
    }

}
